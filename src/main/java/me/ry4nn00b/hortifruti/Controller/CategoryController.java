package me.ry4nn00b.hortifruti.Controller;

import me.ry4nn00b.hortifruti.Model.CategoryModel;
import me.ry4nn00b.hortifruti.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE CATEGORIAS FUNCIONANDO!";
    }

    //ENDPOINT - Create Category
    @PostMapping
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryModel category) {
        CategoryModel saved = categoryService.categorySave(category);
        return ResponseEntity.ok(saved);
    }

    //ENDPOINT - List Categories
    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategories() {
        List<CategoryModel> categories = categoryService.categoryList();
        return ResponseEntity.ok(categories);
    }

    //ENDPOINT - Find By ID Category
    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable String id) {
        Optional<CategoryModel> category = categoryService.categoryFindByID(id);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Update Category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable String id, @RequestBody CategoryModel newCategory) {
        return categoryService.categoryFindByID(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    category.setDescription(newCategory.getDescription());
                    CategoryModel updated = categoryService.categorySave(category);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        if (categoryService.categoryFindByID(id).equals(id)) {
            categoryService.categoryDelete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
