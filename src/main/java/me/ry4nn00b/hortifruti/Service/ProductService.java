package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.ProductModel;
import me.ry4nn00b.hortifruti.Repository.IProductRepository;

import java.util.List;

public class ProductService {

    private final IProductRepository repository;

    //Method's
    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }
    public List<ProductModel> productList(){
        return repository.findAll();
    }
    public ProductModel productSave(ProductModel product){
        return repository.save(product);
    }
    public void productDelete(String id){
        repository.deleteById(id);
    }

}
