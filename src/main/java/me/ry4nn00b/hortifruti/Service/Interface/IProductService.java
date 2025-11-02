package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.DTOs.ProductRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.ProductResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.ProductUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<ProductResponseDTO> productList();
    Optional<ProductResponseDTO> productFindById(String id);
    ProductResponseDTO productSave(ProductRequestDTO requestDTO);
    Optional<ProductResponseDTO> productUpdate(String id, ProductUpdateDTO updateDTO);
    void productDelete(String id);

}
