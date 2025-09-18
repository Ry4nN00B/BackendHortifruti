package me.ry4nn00b.hortifruti.Repository;

import me.ry4nn00b.hortifruti.Model.CategoryModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICategoryRepository extends MongoRepository<CategoryModel, String> {
}
