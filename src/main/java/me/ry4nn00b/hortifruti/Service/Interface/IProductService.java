package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.Model.ProductModel;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<ProductModel> productList();
    Optional<ProductModel> productFindById(String id);
    ProductModel productSave(ProductModel product);
    void productDelete(String id);

}
