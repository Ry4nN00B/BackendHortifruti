package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Enum.SaleStatus;
import me.ry4nn00b.hortifruti.Model.ProductModel;
import me.ry4nn00b.hortifruti.Model.SaleItemModel;
import me.ry4nn00b.hortifruti.Model.SaleModel;
import me.ry4nn00b.hortifruti.Model.StockModel;
import me.ry4nn00b.hortifruti.Repository.IProductRepository;
import me.ry4nn00b.hortifruti.Repository.ISaleRepository;
import me.ry4nn00b.hortifruti.Service.Interface.ISaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleService implements ISaleService {

    private final ISaleRepository saleRepository;
    private final IProductRepository productRepository;
    private final PromotionService promotionService;
    private final StockService stockService;

    public SaleService(ISaleRepository saleRepository, IProductRepository productRepository, PromotionService promotionService, StockService stockService) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.promotionService = promotionService;
        this.stockService = stockService;
    }

    //List All Sales
    @Override
    public List<SaleModel> saleList() {
        return saleRepository.findAll();
    }

    //Find Sale By ID
    @Override
    public Optional<SaleModel> saleFindById(String saleId) {
        return saleRepository.findById(saleId);
    }

    //Delete Sale
    @Override
    public void saleDeleteId(String saleId) {
        saleRepository.deleteById(saleId);
    }

    //Register New Sale
    @Override
    @Transactional
    public SaleModel saleRegister(SaleModel sale) {
        if (sale.getItens() == null || sale.getItens().isEmpty()) {
            throw new IllegalArgumentException("Hortifruti Erro: Não foi possível encontrar nenhum produto nesta venda!");
        }

        //Apply Promotion Price
        for (SaleItemModel item : sale.getItens()) {
            ProductModel product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Hortifruti Erro: Produto não encontrado (ID: " + item.getProductId() + ")"
                    ));

            double priceWithPromotion = promotionService.applyPromotion(product.getId(), product.getPrice());
            item.setUnitPrice(priceWithPromotion);
        }

        //Calculate Total
        double total = sale.getItens().stream()
                .mapToDouble(i -> i.getAmount() * i.getUnitPrice())
                .sum();
        sale.setTotal(total);

        sale.setDateTime(LocalDateTime.now());
        sale.setStatus(SaleStatus.PENDING);

        return saleRepository.save(sale);
    }

    //Confirm Sale and Remove From Stock
    @Override
    @Transactional
    public SaleModel confirmPayment(String saleId) {
        SaleModel sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Venda não encontrada. ID: " + saleId));

        if (sale.getStatus() == SaleStatus.CONFIRMED) {
            throw new IllegalArgumentException("Hortifruti Erro: Venda já está confirmada.");
        }

        //Consume Stock Using the FIFO Standard
        for (SaleItemModel item : sale.getItens()) {
            double remaining = item.getAmount();
            List<StockModel> batches = stockService.stockByProductId(item.getProductId())
                    .stream()
                    .sorted(Comparator.comparing(StockModel::getValidity))
                    .collect(Collectors.toList());

            for (StockModel batch : batches) {
                if (remaining <= 0) break;

                double deduct = Math.min(batch.getAmount(), remaining);
                batch.setAmount(batch.getAmount() - deduct);
                stockService.stockSave(batch);
                remaining -= deduct;
            }

            if (remaining > 0) {
                throw new IllegalArgumentException("Hortifruti Erro: Estoque insuficiente para o produto ID: " + item.getProductId());
            }
        }

        sale.setStatus(SaleStatus.CONFIRMED);
        return saleRepository.save(sale);
    }

    //Cancel Sale And Restore Stock
    @Override
    @Transactional
    public SaleModel cancelSale(String saleId) {
        SaleModel sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Venda não encontrada. ID: " + saleId));

        if (sale.getStatus() == SaleStatus.CANCELLED) {
            throw new IllegalArgumentException("Hortifruti Erro: Venda já está cancelada.");
        }

        if (sale.getStatus() == SaleStatus.CONFIRMED) {
            for (SaleItemModel item : sale.getItens()) {
                StockModel newBatch = new StockModel();
                newBatch.setProductId(item.getProductId());
                newBatch.setAmount(item.getAmount());
                newBatch.setEntryDate(LocalDate.now());
                newBatch.setValidity(null);
                stockService.stockSave(newBatch);
            }
        }

        sale.setStatus(SaleStatus.CANCELLED);
        return saleRepository.save(sale);
    }

    //Update Sale
    @Override
    @Transactional
    public SaleModel saleUpdate(String saleId, SaleModel newSale) {
        SaleModel existing = saleRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Venda não encontrada com ID: " + saleId));

        if (newSale.getItens() != null && !newSale.getItens().isEmpty()) {
            existing.setItens(newSale.getItens());
        }
        if (newSale.getTotal() != null) {
            existing.setTotal(newSale.getTotal());
        }

        existing.setDateTime(LocalDateTime.now());
        return saleRepository.save(existing);
    }

    //Remove Item From Sale
    @Override
    @Transactional
    public SaleModel removeItemFromSale(String saleId, String productId) {
        SaleModel sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Venda não encontrada. ID: " + saleId));

        if (sale.getStatus() == SaleStatus.CONFIRMED) {
            throw new IllegalArgumentException("Hortifruti Erro: Não é possível remover itens de uma venda já confirmada.");
        }

        Optional<SaleItemModel> itemOpt = sale.getItens().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst();

        if (itemOpt.isEmpty()) {
            throw new IllegalArgumentException("Hortifruti Erro: Produto não encontrado nesta venda: " + productId);
        }

        SaleItemModel item = itemOpt.get();
        sale.getItens().remove(item);

        double total = sale.getItens().stream()
                .mapToDouble(i -> i.getAmount() * i.getUnitPrice())
                .sum();
        sale.setTotal(total);

        return saleRepository.save(sale);
    }
}

