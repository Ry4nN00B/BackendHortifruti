package me.ry4nn00b.hortifruti.Controller;

import me.ry4nn00b.hortifruti.Model.ProductModel;
import me.ry4nn00b.hortifruti.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Product Method's
    @GetMapping
    public List<ProductModel> productList() {
        return productService.productList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable String productId) {
        return productService.productFindById(productId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductModel> productSave(@RequestBody ProductModel product) {
        return ResponseEntity.ok(productService.productSave(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable String productId) {
        productService.productDelete(productId);
        return ResponseEntity.noContent().build();
    }

}
