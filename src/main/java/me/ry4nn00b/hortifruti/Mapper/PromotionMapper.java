package me.ry4nn00b.hortifruti.Mapper;

import me.ry4nn00b.hortifruti.DTOs.PromotionRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.PromotionResponseDTO;
import me.ry4nn00b.hortifruti.Model.PromotionModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapper {

    private final ModelMapper modelMapper;

    public PromotionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //PromotionRequestDTO -> PromotionModel
    public PromotionModel toModel(PromotionRequestDTO dto) {
        return modelMapper.map(dto, PromotionModel.class);
    }

    //PromotionModel -> PromotionResponseDTO
    public PromotionResponseDTO toResponseDTO(PromotionModel model) {

        PromotionResponseDTO dto = modelMapper.map(model, PromotionResponseDTO.class);
        dto.setActive(model.getEndDate().isAfter(java.time.LocalDate.now()));

        return dto;
    }
}
