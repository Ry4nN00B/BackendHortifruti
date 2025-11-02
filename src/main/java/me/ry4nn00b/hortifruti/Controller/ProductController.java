package me.ry4nn00b.hortifruti.Controller;

import jakarta.validation.Valid;
import me.ry4nn00b.hortifruti.DTOs.ProductRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.ProductResponseDTO;
import me.ry4nn00b.hortifruti.Mapper.ProductMapper;
import me.ry4nn00b.hortifruti.DTOs.ProductUpdateDTO;
import me.ry4nn00b.hortifruti.Service.Interface.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    // ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE PRODUTOS FUNCIONANDO!";
    }

    // ENDPOINT - List Product's
    @GetMapping
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {

        List<ProductResponseDTO> responseDTOs = productService.productList();
        return ResponseEntity.ok(responseDTOs);
    }

    // ENDPOINT - Find By ID Product
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id) {

        return productService.productFindById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ENDPOINT - Create Product
    @PostMapping
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {

        ProductResponseDTO responseDTO = productService.productSave(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // ENDPOINT - Update Product
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String id, @RequestBody ProductUpdateDTO updateDTO) {

        return productService.productUpdate(id, updateDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ENDPOINT - Delete Product
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {

        productService.productDelete(id);
        return ResponseEntity.noContent().build();
    }

}
