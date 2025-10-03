package me.ry4nn00b.hortifruti.Controller;

import me.ry4nn00b.hortifruti.Model.CategoryModel;
import me.ry4nn00b.hortifruti.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //Category Method's
    @GetMapping
    public List<CategoryModel> categoryList(){
        return categoryService.categoryList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> findById(@PathVariable String categoryId){
        return categoryService.categoryFindByID(categoryId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryModel> saveCategory(@RequestBody CategoryModel categoryModel){
        return ResponseEntity.ok(categoryService.categorySave(categoryModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable String categoryId){
        categoryService.categoryDelete(categoryId);
        return ResponseEntity.noContent().build();
    }

}
