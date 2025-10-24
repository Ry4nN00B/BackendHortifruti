package me.ry4nn00b.hortifruti.Mapper;

import me.ry4nn00b.hortifruti.Model.DTOs.SupplierRequestDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.SupplierResponseDTO;
import me.ry4nn00b.hortifruti.Model.SupplierModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    private final ModelMapper modelMapper;

    public SupplierMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //SupplierRequestDTO -> SupplierModel
    public SupplierModel toModel(SupplierRequestDTO dto) {
        return modelMapper.map(dto, SupplierModel.class);
    }

    //SupplierModel -> SupplierResponseDTO
    public SupplierResponseDTO toResponseDTO(SupplierModel model) {
        return modelMapper.map(model, SupplierResponseDTO.class);
    }
}
