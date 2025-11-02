package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.DTOs.CategoryRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.CategoryResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.CategoryUpdateDTO;
import me.ry4nn00b.hortifruti.Model.CategoryModel;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<CategoryResponseDTO> categoryList();
    Optional<CategoryResponseDTO> categoryFindByID(String categoryId);
    CategoryResponseDTO categorySave(CategoryRequestDTO categoryDTO);
    Optional<CategoryResponseDTO> categoryUpdate(String id, CategoryUpdateDTO updateDTO);
    void categoryDelete(String id);

}
