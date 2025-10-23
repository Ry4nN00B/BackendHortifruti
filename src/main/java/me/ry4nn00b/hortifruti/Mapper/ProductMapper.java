package me.ry4nn00b.hortifruti.Mapper;

import me.ry4nn00b.hortifruti.DTOs.ProductRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.ProductResponseDTO;
import me.ry4nn00b.hortifruti.Model.ProductModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //ProductRequestDTO -> ProductModel
    public ProductModel toModel(ProductRequestDTO dto) {

        ProductModel model = new ProductModel();
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setPrice(dto.getPrice());
        model.setCategoryId(dto.getCategoryId());
        model.setSupplierId(dto.getSupplierId());
        model.setSoldByWeight(dto.getSoldByWeight());

        return model;
    }

    //ProductModel -> ProductResponseDTO
    public ProductResponseDTO toResponseDTO(ProductModel model) {

        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        dto.setPrice(model.getPrice());
        dto.setCategoryId(model.getCategoryId());
        dto.setSupplierId(model.getSupplierId());
        dto.setSoldByWeight(model.getSoldByWeight());

        return dto;
    }
}
