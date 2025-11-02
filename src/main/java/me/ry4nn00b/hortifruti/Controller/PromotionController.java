package me.ry4nn00b.hortifruti.Controller;

import jakarta.validation.Valid;
import me.ry4nn00b.hortifruti.DTOs.PromotionRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.PromotionResponseDTO;
import me.ry4nn00b.hortifruti.Mapper.PromotionMapper;
import me.ry4nn00b.hortifruti.DTOs.PromotionUpdateDTO;
import me.ry4nn00b.hortifruti.Model.PromotionModel;
import me.ry4nn00b.hortifruti.Service.Interface.IPromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promocoes")
public class PromotionController {

    private final IPromotionService promotionService;

    public PromotionController(IPromotionService promotionService) {
        this.promotionService = promotionService;
    }

    //ENDPOINT - Test API
    @GetMapping("/")
    public String home() {
        return "API DE PROMOÇÕES FUNCIONANDO!";
    }

    //ENDPOINT - List Active Promotions
    @GetMapping
    @PreAuthorize("hasAnyAuthority('GERENTE', 'OPERADOR')")
    public ResponseEntity<List<PromotionResponseDTO>> getActivePromotions() {

        List<PromotionResponseDTO> responseDTOs = promotionService.promotionActiveList();
        return ResponseEntity.ok(responseDTOs);
    }

    //ENDPOINT - Get Promotion By ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'OPERADOR')")
    public ResponseEntity<PromotionResponseDTO> getPromotionById(@PathVariable String id) {

        return promotionService.promotionFindById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Create Promotion
    @PostMapping
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<PromotionResponseDTO> createPromotion(@Valid @RequestBody PromotionRequestDTO requestDTO) {

        PromotionResponseDTO responseDTO = promotionService.promotionSave(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    //ENDPOINT - Update Promotion
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<PromotionResponseDTO> updatePromotion(@PathVariable String id, @Valid @RequestBody PromotionUpdateDTO updateDTO) {

        return promotionService.promotionUpdate(id, updateDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Promotion
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<Void> deletePromotion(@PathVariable String id) {

        promotionService.promotionDelete(id);
        return ResponseEntity.noContent().build();
    }
}
