package me.ry4nn00b.hortifruti.Mapper;

import me.ry4nn00b.hortifruti.DTOs.SaleItemRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.SaleItemResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.SaleRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.SaleResponseDTO;
import me.ry4nn00b.hortifruti.Model.SaleItemModel;
import me.ry4nn00b.hortifruti.Model.SaleModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleMapper {

    //SaleRequestDTO -> SaleModel
    public SaleModel toModel(SaleRequestDTO dto) {

        SaleModel sale = new SaleModel();
        sale.setOperatorId(dto.getOperatorId());
        sale.setPaymentMethod(dto.getPaymentMethod());

        if (dto.getItems() != null && !dto.getItems().isEmpty()) {
            sale.setItems(toItemModel(dto.getItems()));
        }

        return sale;
    }

    //List SaleItemRequestDTO ->List SaleItemModel
    public List<SaleItemModel> toItemModel(List<SaleItemRequestDTO> dtos) {
        if (dtos == null) return new ArrayList<>();
        return dtos.stream()
                .map(this::toItemModel)
                .collect(Collectors.toList());
    }

    //SaleItemRequestDTO -> SaleItemModel
    public SaleItemModel toItemModel(SaleItemRequestDTO dto) {

        SaleItemModel item = new SaleItemModel();
        item.setProductId(dto.getProductId());
        item.setAmount(dto.getAmount());
        item.setUnitPrice(BigDecimal.ZERO);

        return item;
    }

    //SaleModel -> SaleResponseDTO
    public SaleResponseDTO toResponseDTO(SaleModel model) {

        SaleResponseDTO dto = new SaleResponseDTO();
        dto.setId(model.getId());
        dto.setDateTime(model.getDateTime());
        dto.setOperatorId(model.getOperatorId());
        dto.setPaymentMethod(model.getPaymentMethod());
        dto.setStatus(model.getStatus());
        dto.setTotal(model.getTotal());

        if (model.getItems() != null && !model.getItems().isEmpty()) {
            dto.setItems(model.getItems().stream()
                    .map(this::toItemResponseDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    //SaleItemModel -> SaleItemResponseDTO
    public SaleItemResponseDTO toItemResponseDTO(SaleItemModel model) {

        SaleItemResponseDTO dto = new SaleItemResponseDTO();
        dto.setProductId(model.getProductId());
        dto.setAmount(model.getAmount());
        dto.setUnitPrice(model.getUnitPrice() != null ? model.getUnitPrice() : BigDecimal.ZERO);

        return dto;
    }

}
