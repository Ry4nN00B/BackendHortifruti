package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.ProductModel;
import me.ry4nn00b.hortifruti.Repository.ICategoryRepository;
import me.ry4nn00b.hortifruti.Repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final IProductRepository repository;
    private final ICategoryRepository categoryRepository;

    //Method's
    public ProductService(IProductRepository repository, ICategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }
    public Optional<ProductModel> productFindById(String id) {
        return repository.findById(id);
    }
    public List<ProductModel> productList(){
        return repository.findAll();
    }
    public ProductModel productSave(ProductModel product){
        categoryRepository.findById(product.getCategoryId()).orElseThrow(() -> new RuntimeException("Hortifruti Erro: Não foi possível encontrar esta categoria de produtos!"));
        return repository.save(product);
    }
    public void productDelete(String id){
        repository.deleteById(id);
    }

    public ProductModel updateStockAmount(String productID, int newAmount) {
        Optional<ProductModel> opt = repository.findById(productID);
        if (opt.isPresent()) {
            ProductModel p = opt.get();
            p.setAmount(newAmount);
            return repository.save(p);
        } else {
            throw new IllegalArgumentException("Hortifruti Erro: Não foi possível encontrar o produto de ID igual a: " + productID + ".");
        }
    }

    public List<ProductModel> closeToExpiration(int dias) {
        LocalDate limit = LocalDate.now().plusDays(dias);
        return repository.findByValidityBefore(limit);
    }

}
