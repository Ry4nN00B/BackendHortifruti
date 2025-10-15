package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.ProductModel;
import me.ry4nn00b.hortifruti.Model.SaleItemModel;
import me.ry4nn00b.hortifruti.Model.SaleModel;
import me.ry4nn00b.hortifruti.Repository.IProductRepository;
import me.ry4nn00b.hortifruti.Repository.ISaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final ISaleRepository saleRepository;
    private final IProductRepository productRepository;

    public SaleService(ISaleRepository saleRepository, IProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    //Get Sale List
    public List<SaleModel> saleList() {
        return saleRepository.findAll();
    }

    //Find Sale By ID
    public Optional<SaleModel> saleFindById(String saleId) {
        return saleRepository.findById(saleId);
    }

    //Delete sale
    public void saleDeleteId(String saleId) {
        saleRepository.deleteById(saleId);
    }

    //Register New Sale
    @Transactional
    public SaleModel saleRegister(SaleModel sale) {
        if (sale.getItens() == null || sale.getItens().isEmpty()) {
            throw new IllegalArgumentException("Hortifruti Erro: Não foi possível encontrar nenhum produto nesta venda!");
        }

        //Stock Manager
        for (SaleItemModel item : sale.getItens()) {
            Optional<ProductModel> opt = productRepository.findById(item.getProductId());
            if (opt.isEmpty()) {
                throw new IllegalArgumentException("Hortifruti Erro: Produto não encontrado (ID: " + item.getProductId() + ").");
            }
            ProductModel p = opt.get();
            if (p.getAmount() < item.getAmount()) {
                throw new IllegalArgumentException("Hortifruti Erro: Estoque insuficiente para o produto: " + p.getName());
            }
            p.setAmount(p.getAmount() - item.getAmount());
            productRepository.save(p);
        }

        //Total Calculate
        if (sale.getTotal() == null) {
            double soma = sale.getItens().stream()
                    .mapToDouble(i -> i.getAmount() * i.getUnitPrice())
                    .sum();
            sale.setTotal(soma);
        }

        sale.setDateTime(LocalDateTime.now());
        return saleRepository.save(sale);
    }

    //Update existing Sale
    @Transactional
    public SaleModel saleUpdate(String saleId, SaleModel newSale) {
        SaleModel existingSale = saleRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Venda não encontrada com ID: " + saleId));

        if (newSale.getItens() != null && !newSale.getItens().isEmpty()) {
            existingSale.setItens(newSale.getItens());
        }
        if (newSale.getTotal() != null) {
            existingSale.setTotal(newSale.getTotal());
        }

        existingSale.setDateTime(LocalDateTime.now());
        return saleRepository.save(existingSale);
    }

    //Delete Specific Item
    @Transactional
    public SaleModel removeItemFromSale(String saleId, String productId) {
        SaleModel sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Venda não encontrada com ID: " + saleId));

        boolean removed = sale.getItens().removeIf(item -> item.getProductId().equals(productId));

        if (!removed) {
            throw new IllegalArgumentException("Hortifruti Erro: Produto com ID " + productId + " não encontrado nesta venda.");
        }

        double novoTotal = sale.getItens().stream()
                .mapToDouble(i -> i.getAmount() * i.getUnitPrice())
                .sum();
        sale.setTotal(novoTotal);

        return saleRepository.save(sale);
    }
}
