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

    //ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE PRODUTOS FUNCIONANDO!";
    }

    //ENDPOINT - Create Product
    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
        ProductModel saved = productService.productSave(product);
        return ResponseEntity.ok(saved);
    }

    //ENDPOINT - List Product's
    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> products = productService.productList();
        return ResponseEntity.ok(products);
    }

    //ENDPOINT - Find By ID Product
    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable String id) {
        return productService.productFindById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Update Product
    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable String id, @RequestBody ProductModel updatedProduct) {
        return productService.productFindById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    product.setCategoryId(updatedProduct.getCategoryId());
                    product.setSupplierId(updatedProduct.getSupplierId());
                    product.setValidity(updatedProduct.getValidity());
                    ProductModel updated = productService.productSave(product);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        if (productService.productFindById(id).isPresent()) {
            productService.productDelete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
