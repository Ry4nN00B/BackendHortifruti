package me.ry4nn00b.hortifruti.Repository;

import me.ry4nn00b.hortifruti.Model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductRepository extends MongoRepository<ProductModel, String> {
}
