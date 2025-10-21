package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.Model.CategoryModel;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<CategoryModel> categoryList();
    Optional<CategoryModel> categoryFindByID(String categoryId);
    CategoryModel categorySave(CategoryModel category);
    void categoryDelete(String id);

}
