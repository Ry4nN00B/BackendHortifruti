package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.DTOs.PromotionRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.PromotionResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.PromotionUpdateDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IPromotionService {

    BigDecimal applyPromotion(String productId, BigDecimal originalPrice);
    List<PromotionResponseDTO> promotionActiveList();
    Optional<PromotionResponseDTO> promotionFindById(String id);
    PromotionResponseDTO promotionSave(PromotionRequestDTO requestDTO);
    Optional<PromotionResponseDTO> promotionUpdate(String id, PromotionUpdateDTO updateDTO);
    void promotionDelete(String id);

}
