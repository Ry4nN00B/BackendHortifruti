package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.Model.PromotionModel;

import java.util.List;
import java.util.Optional;

public interface IPromotionService {

    Double applyPromotion(String productId, Double originalPrice);
    List<PromotionModel> promotionActiveList();
    Optional<PromotionModel> productFindById(String id);
    PromotionModel promotionSave(PromotionModel p);
    void promotionDelete(String id);

}
