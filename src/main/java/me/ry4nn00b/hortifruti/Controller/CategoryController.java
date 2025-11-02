package me.ry4nn00b.hortifruti.Controller;

import jakarta.validation.Valid;
import me.ry4nn00b.hortifruti.DTOs.CategoryRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.CategoryResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.CategoryUpdateDTO;
import me.ry4nn00b.hortifruti.Service.Interface.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService){
        this.categoryService = categoryService;
    }

    //ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE CATEGORIAS FUNCIONANDO!";
    }

    //ENDPOINT - List Categories
    @GetMapping
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {

        List<CategoryResponseDTO> categories = categoryService.categoryList();
        return ResponseEntity.ok(categories);
    }

    //ENDPOINT - Find By ID Category
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable String id) {
        return categoryService.categoryFindByID(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Create Category
    @PostMapping
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) {

        CategoryResponseDTO saved = categoryService.categorySave(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //ENDPOINT - Update Category
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable String id, @Valid @RequestBody CategoryUpdateDTO updateDTO) {

        return categoryService.categoryUpdate(id, updateDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Category
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {

        categoryService.categoryDelete(id);
        return ResponseEntity.noContent().build();
    }

}
