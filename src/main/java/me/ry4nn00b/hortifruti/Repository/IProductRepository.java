package me.ry4nn00b.hortifruti.Repository;

import me.ry4nn00b.hortifruti.Model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IProductRepository extends MongoRepository<ProductModel, String> {
    List<ProductModel> findByCategoryId(String categoryId);
    List<ProductModel> findByAmountLessThan(Integer amount);
    List<ProductModel> findByValidityBefore(LocalDate date);
}
