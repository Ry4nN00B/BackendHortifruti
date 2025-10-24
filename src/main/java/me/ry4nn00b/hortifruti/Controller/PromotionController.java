package me.ry4nn00b.hortifruti.Controller;

import jakarta.validation.Valid;
import me.ry4nn00b.hortifruti.Model.DTOs.PromotionRequestDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.PromotionResponseDTO;
import me.ry4nn00b.hortifruti.Mapper.PromotionMapper;
import me.ry4nn00b.hortifruti.Model.DTOs.PromotionUpdateDTO;
import me.ry4nn00b.hortifruti.Model.PromotionModel;
import me.ry4nn00b.hortifruti.Service.Interface.IPromotionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promocoes")
public class PromotionController {

    private final IPromotionService promotionService;
    private final PromotionMapper promotionMapper;

    public PromotionController(IPromotionService promotionService, PromotionMapper promotionMapper) {
        this.promotionService = promotionService;
        this.promotionMapper = promotionMapper;
    }

    //ENDPOINT - Test API
    @GetMapping("/")
    public String home() {
        return "API DE PROMOÇÕES FUNCIONANDO!";
    }

    //ENDPOINT - Create Promotion
    @PostMapping
    public ResponseEntity<PromotionResponseDTO> createPromotion(@Valid @RequestBody PromotionRequestDTO requestDTO) {

        PromotionModel model = promotionMapper.toModel(requestDTO);
        PromotionModel saved = promotionService.promotionSave(model);
        PromotionResponseDTO responseDTO = promotionMapper.toResponseDTO(saved);

        return ResponseEntity.ok(responseDTO);
    }

    //ENDPOINT - List Active Promotions
    @GetMapping
    public ResponseEntity<List<PromotionResponseDTO>> getActivePromotions() {
        List<PromotionModel> activePromotions = promotionService.promotionActiveList();
        List<PromotionResponseDTO> responseDTOs = activePromotions.stream().map(promotionMapper::toResponseDTO).collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }

    //ENDPOINT - Get Promotion By ID
    @GetMapping("/{id}")
    public ResponseEntity<PromotionResponseDTO> getPromotionById(@PathVariable String id) {
        return promotionService.productFindById(id)
                .map(promotionMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Update Promotion
    @PutMapping("/{id}")
    public ResponseEntity<PromotionResponseDTO> updatePromotion(@PathVariable String id, @Valid @RequestBody PromotionUpdateDTO updateDTO) {
        return promotionService.productFindById(id)
                .map(existing -> {
                    if(updateDTO.getProductId() != null) existing.setProductId(updateDTO.getProductId());
                    if(updateDTO.getType() != null) existing.setType(updateDTO.getType());
                    if(updateDTO.getValue() != null) existing.setValue(updateDTO.getValue());
                    if(updateDTO.getStartDate() != null) existing.setStartDate(updateDTO.getStartDate());
                    if(updateDTO.getEndDate() != null) existing.setEndDate(updateDTO.getEndDate());

                    PromotionModel updated = promotionService.promotionSave(existing);
                    PromotionResponseDTO responseDTO = promotionMapper.toResponseDTO(updated);

                    return ResponseEntity.ok(responseDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Promotion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable String id) {
        return promotionService.productFindById(id)
                .map(existing -> {
                    promotionService.promotionDelete(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
