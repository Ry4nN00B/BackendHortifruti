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

    //Method's
    public SaleService(ISaleRepository saleRepository, IProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    //Method's
    public List<SaleModel> saleList() {
        return saleRepository.findAll();
    }
    public Optional<SaleModel> saleFindById(String saleId) {
        return saleRepository.findById(saleId);
    }
    public void saleDeleteId(String saleId){
        saleRepository.deleteById(saleId);
    }

    //Sale Register/Manager
    @Transactional
    public SaleModel saleRegister(SaleModel sale) {

        if (sale.getItens() == null || sale.getItens().isEmpty()) {
            throw new IllegalArgumentException("Hortifruti Erro: Não foi possível encontrar nenhum produto nesta venda!");
        }

        //Stock Manager
        for (SaleItemModel item : sale.getItens()) {
            Optional<ProductModel> opt = productRepository.findById(item.getProductId());
            if (opt.isEmpty()) {
                throw new IllegalArgumentException("Hortifruti Erro: Não foi possível encontrar o produto de ID igual a: " + item.getProductId() + ".");
            }
            ProductModel p = opt.get();
            if (p.getAmount() < item.getAmount()) {
                throw new IllegalArgumentException("Hortifruti Erro: Estoque insuficiente para o produto: " + p.getName());
            }
            p.setAmount(p.getAmount() - item.getAmount());
            productRepository.save(p);
        }

        //Calculate Total
        if (sale.getTotal() == null) {
            double soma = sale.getItens().stream()
                    .mapToDouble(i -> i.getAmount() * i.getUnitPrice())
                    .sum();
            sale.setTotal(soma);
        }

        sale.setDateTime(LocalDateTime.now());
        return saleRepository.save(sale);
    }
}
