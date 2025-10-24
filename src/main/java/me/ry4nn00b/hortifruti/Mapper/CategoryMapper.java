package me.ry4nn00b.hortifruti.Mapper;

import me.ry4nn00b.hortifruti.Model.DTOs.CategoryRequestDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.CategoryResponseDTO;
import me.ry4nn00b.hortifruti.Model.CategoryModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    //CategoryRequestDTO -> CategoryModel
    public CategoryModel toModel(CategoryRequestDTO dto) {
        return modelMapper.map(dto, CategoryModel.class);
    }

    //CategoryModel -> CategoryResponseDTO
    public CategoryResponseDTO toResponseDTO(CategoryModel model) {
        return modelMapper.map(model, CategoryResponseDTO.class);
    }


}
