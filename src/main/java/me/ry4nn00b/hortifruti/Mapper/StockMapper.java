package me.ry4nn00b.hortifruti.Mapper;

import me.ry4nn00b.hortifruti.DTOs.StockRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.StockResponseDTO;
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
        return modelMapper.map(dto, StockModel.class);
    }

    //StockModel -> StockResponseDTO
    public StockResponseDTO toResponseDTO(StockModel model) {
        return modelMapper.map(model, StockResponseDTO.class);
    }
}
