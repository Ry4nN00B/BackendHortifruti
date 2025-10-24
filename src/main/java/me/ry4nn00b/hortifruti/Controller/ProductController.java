package me.ry4nn00b.hortifruti.Controller;

import jakarta.validation.Valid;
import me.ry4nn00b.hortifruti.Model.DTOs.ProductRequestDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.ProductResponseDTO;
import me.ry4nn00b.hortifruti.Mapper.ProductMapper;
import me.ry4nn00b.hortifruti.Model.DTOs.ProductUpdateDTO;
import me.ry4nn00b.hortifruti.Model.ProductModel;
import me.ry4nn00b.hortifruti.Service.Interface.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    private final IProductService productService;
    private final ProductMapper productMapper;

    public ProductController(IProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    //ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE PRODUTOS FUNCIONANDO!";
    }

    //ENDPOINT - Create Product
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {

        ProductModel model = productMapper.toModel(requestDTO);
        ProductModel saved = productService.productSave(model);
        ProductResponseDTO responseDTO = productMapper.toResponseDTO(saved);

        return ResponseEntity.ok(responseDTO);
    }

    //ENDPOINT - List Product's
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductModel> products = productService.productList();
        List<ProductResponseDTO> responseDTOs = products.stream().map(productMapper::toResponseDTO).toList();

        return ResponseEntity.ok(responseDTOs);
    }

    //ENDPOINT - Find By ID Product
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id) {
        return productService.productFindById(id)
                .map(productMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Update Product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String id, @Valid @RequestBody ProductUpdateDTO updateDTO) {

        return productService.productFindById(id)
                .map(existing -> {
                    if(updateDTO.getName() != null) existing.setName(updateDTO.getName());
                    if(updateDTO.getDescription() != null) existing.setDescription(updateDTO.getDescription());
                    if(updateDTO.getPrice() != null) existing.setPrice(updateDTO.getPrice());
                    if(updateDTO.getCategoryId() != null) existing.setCategoryId(updateDTO.getCategoryId());
                    if(updateDTO.getSupplierId() != null) existing.setSupplierId(updateDTO.getSupplierId());

                    ProductModel updated = productService.productSave(existing);
                    ProductResponseDTO responseDTO = productMapper.toResponseDTO(updated);

                    return ResponseEntity.ok(responseDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        return productService.productFindById(id)
                .map(existing -> {
                    productService.productDelete(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
