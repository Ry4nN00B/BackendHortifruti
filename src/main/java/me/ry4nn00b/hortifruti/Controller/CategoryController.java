package me.ry4nn00b.hortifruti.Controller;

import jakarta.validation.Valid;
import me.ry4nn00b.hortifruti.Model.DTOs.CategoryRequestDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.CategoryResponseDTO;
import me.ry4nn00b.hortifruti.Mapper.CategoryMapper;
import me.ry4nn00b.hortifruti.Model.CategoryModel;
import me.ry4nn00b.hortifruti.Model.DTOs.CategoryUpdateDTO;
import me.ry4nn00b.hortifruti.Service.Interface.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

    private final ICategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(ICategoryService categoryService, CategoryMapper categoryMapper){
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    //ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE CATEGORIAS FUNCIONANDO!";
    }

    //ENDPOINT - Create Category
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) {

        CategoryModel model = categoryMapper.toModel(requestDTO);
        CategoryModel saved = categoryService.categorySave(model);
        CategoryResponseDTO responseDTO = categoryMapper.toResponseDTO(saved);

        return ResponseEntity.ok(responseDTO);
    }

    //ENDPOINT - List Categories
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryModel> categories = categoryService.categoryList();
        List<CategoryResponseDTO> responseDTO = categories.stream().map(categoryMapper::toResponseDTO).toList();

        return ResponseEntity.ok(responseDTO);
    }

    //ENDPOINT - Find By ID Category
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable String id) {
        return categoryService.categoryFindByID(id)
                .map(categoryMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Update Category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable String id, @Valid @RequestBody CategoryUpdateDTO updateDTO) {

        return categoryService.categoryFindByID(id)
                .map(existing -> {
                    if(updateDTO.getName() != null) existing.setName(updateDTO.getName());
                    if(updateDTO.getDescription() != null) existing.setDescription(updateDTO.getDescription());

                    CategoryModel updated = categoryService.categorySave(existing);
                    CategoryResponseDTO responseDTO = categoryMapper.toResponseDTO(updated);

                    return ResponseEntity.ok(responseDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        return categoryService.categoryFindByID(id)
                .map(existing -> {
                    categoryService.categoryDelete(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
