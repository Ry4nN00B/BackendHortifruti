package me.ry4nn00b.hortifruti.Mapper;

import me.ry4nn00b.hortifruti.Model.DTOs.StockRequestDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.StockResponseDTO;
import me.ry4nn00b.hortifruti.Model.StockModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    private final ModelMapper modelMapper;

    public StockMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //StockRequestDTO -> StockModel
    public StockModel toModel(StockRequestDTO dto) {

        StockModel model = new StockModel();
        model.setProductId(dto.getProductId());
        model.setAmount(dto.getAmount());
        model.setValidity(dto.getValidity());

        return model;

    }

    //StockModel -> StockResponseDTO
    public StockResponseDTO toResponseDTO(StockModel model) {

        StockResponseDTO dto = new StockResponseDTO();
        dto.setId(model.getId());
        dto.setProductId(model.getProductId());
        dto.setAmount(model.getAmount());
        dto.setEntryDate(model.getEntryDate());
        dto.setValidity(model.getValidity());

        return dto;

    }
}
