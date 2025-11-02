package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.DTOs.SaleRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.SaleResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.StockRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.StockResponseDTO;
import me.ry4nn00b.hortifruti.Enum.SaleStatus;
import me.ry4nn00b.hortifruti.Mapper.SaleMapper;
import me.ry4nn00b.hortifruti.Model.*;
import me.ry4nn00b.hortifruti.Repository.IProductRepository;
import me.ry4nn00b.hortifruti.Repository.ISaleRepository;
import me.ry4nn00b.hortifruti.Service.Interface.ISaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService implements ISaleService {

    private final ISaleRepository saleRepository;
    private final IProductRepository productRepository;
    private final PromotionService promotionService;
    private final StockService stockService;
    private final SaleMapper saleMapper;

    public SaleService(ISaleRepository saleRepository, IProductRepository productRepository, PromotionService promotionService, StockService stockService, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.promotionService = promotionService;
        this.stockService = stockService;
        this.saleMapper = saleMapper;
    }

    //List All Sales
    @Override
    public List<SaleResponseDTO> saleList() {
        return saleRepository.findAll().stream()
                .map(saleMapper::toResponseDTO)
                .toList();
    }

    //Find Sale By ID
    @Override
    public Optional<SaleResponseDTO> saleFindById(String saleId) {
        return saleRepository.findById(saleId)
                .map(saleMapper::toResponseDTO);
    }

    //Register New Sale
    @Override
    @Transactional
    public SaleResponseDTO saleRegister(SaleRequestDTO saleDTO) {
        if (saleDTO.getItems() == null || saleDTO.getItems().isEmpty()) {
            throw new IllegalArgumentException("Hortifruti Erro: Não foi possível encontrar nenhum produto nesta venda!");
        }

        SaleModel sale = saleMapper.toModel(saleDTO);

        //Apply Promotion
        for (SaleItemModel item : sale.getItems()) {
            ProductModel product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Hortifruti Erro: Produto não encontrado (ID: " + item.getProductId() + ")"
                    ));

            BigDecimal priceWithPromotion = promotionService.applyPromotion(product.getId(), product.getPrice());
            item.setUnitPrice(priceWithPromotion.setScale(2, RoundingMode.HALF_UP));
        }

        //Calculate Total
        BigDecimal total = sale.getItems().stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        sale.setTotal(total);

        sale.setDateTime(LocalDateTime.now());
        sale.setStatus(SaleStatus.PENDING);

        SaleModel saved = saleRepository.save(sale);
        return saleMapper.toResponseDTO(saved);
    }

    //Update Sale
    @Override
    @Transactional
    public Optional<SaleResponseDTO> saleUpdate(String id, SaleRequestDTO saleDTO) {
        return Optional.ofNullable(saleRepository.findById(id).map(sale -> {

            if (saleDTO.getItems() != null && !saleDTO.getItems().isEmpty()) {
                sale.setItems(saleMapper.toItemModel(saleDTO.getItems()));

                BigDecimal total = sale.getItems().stream()
                        .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getAmount())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .setScale(2, RoundingMode.HALF_UP);

                sale.setTotal(total);
            }

            if (saleDTO.getPaymentMethod() != null) sale.setPaymentMethod(saleDTO.getPaymentMethod());
            if (saleDTO.getOperatorId() != null) sale.setOperatorId(saleDTO.getOperatorId());

            sale.setDateTime(LocalDateTime.now());

            SaleModel updated = saleRepository.save(sale);
            return saleMapper.toResponseDTO(updated);
        }).orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Venda não encontrada. ID: " + id)));
    }

    //Delete Sale
    @Override
    public void saleDeleteId(String saleId) {
        saleRepository.deleteById(saleId);
    }

    //Confirm Payment
    @Override
    @Transactional
    public SaleResponseDTO confirmPayment(String id) {
        return saleRepository.findById(id).map(sale -> {

            if (sale.getStatus() == SaleStatus.CONFIRMED) {
                throw new IllegalArgumentException("Hortifruti Erro: Venda já está confirmada.");
            }

            for (SaleItemModel item : sale.getItems()) {
                double remaining = item.getAmount();

                List<StockResponseDTO> batches = stockService.stockByProductId(item.getProductId())
                        .stream()
                        .sorted(Comparator.comparing(StockResponseDTO::getValidity))
                        .toList();

                for (StockResponseDTO batch : batches) {
                    if (remaining <= 0) break;

                    double deduct = Math.min(batch.getAmount(), remaining);
                    batch.setAmount(batch.getAmount() - deduct);

                    StockRequestDTO updateRequest = new StockRequestDTO(batch.getProductId(), batch.getAmount(), batch.getValidity());

                    stockService.stockSave(updateRequest);
                    remaining -= deduct;
                }

                if (remaining > 0) {
                    throw new IllegalArgumentException("Hortifruti Erro: Estoque insuficiente para o produto ID: " + item.getProductId());
                }
            }

            sale.setStatus(SaleStatus.CONFIRMED);
            SaleModel updatedSale = saleRepository.save(sale);

            return saleMapper.toResponseDTO(updatedSale);
        }).orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Venda não encontrada. ID: " + id));
    }

    //Cancel Sale And Restore Stock
    @Override
    @Transactional
    public SaleResponseDTO cancelSale(String id) {
        return saleRepository.findById(id).map(sale -> {

            if (sale.getStatus() == SaleStatus.CANCELLED) {
                throw new IllegalArgumentException("Hortifruti Erro: Venda já está cancelada.");
            }

            if (sale.getStatus() == SaleStatus.CONFIRMED) {
                for (SaleItemModel item : sale.getItems()) {
                    StockRequestDTO newBatch = new StockRequestDTO();
                    newBatch.setProductId(item.getProductId());
                    newBatch.setAmount(item.getAmount());
                    newBatch.setValidity(null);
                    stockService.stockSave(newBatch);
                }
            }

            sale.setStatus(SaleStatus.CANCELLED);
            return saleMapper.toResponseDTO(saleRepository.save(sale));

        }).orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Venda não encontrada. ID: " + id));
    }

    //Remove Item From Sale
    @Override
    @Transactional
    public Optional<SaleResponseDTO> removeItemFromSale(String id, String productId) {
        return Optional.ofNullable(saleRepository.findById(id).map(sale -> {
            if (sale.getStatus() == SaleStatus.CONFIRMED) {
                throw new IllegalArgumentException("Hortifruti Erro: Não é possível remover itens de uma venda já confirmada.");
            }

            Optional<SaleItemModel> itemOpt = sale.getItems().stream()
                    .filter(i -> i.getProductId().equals(productId))
                    .findFirst();

            if (itemOpt.isEmpty()) {
                throw new IllegalArgumentException("Hortifruti Erro: Produto não encontrado nesta venda: " + productId);
            }

            sale.getItems().remove(itemOpt.get());

            BigDecimal total = sale.getItems().stream()
                    .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getAmount())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .setScale(2, RoundingMode.HALF_UP);

            sale.setTotal(total);

            return saleMapper.toResponseDTO(saleRepository.save(sale));
        }).orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Venda não encontrada. ID: " + id)));
    }



}

