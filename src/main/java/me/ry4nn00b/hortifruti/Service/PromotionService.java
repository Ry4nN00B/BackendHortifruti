package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.PromotionModel;
import me.ry4nn00b.hortifruti.Repository.IPromotionRepository;
import me.ry4nn00b.hortifruti.Service.Interface.IPromotionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService implements IPromotionService {

    private final IPromotionRepository repository;

    public PromotionService(IPromotionRepository repository) {
        this.repository = repository;
    }

    //Automatic Apply Promotion
    @Override
    public Double applyPromotion(String productId, Double originalPrice) {
        LocalDate today = LocalDate.now();
        Optional<PromotionModel> promotion = repository.findActivePromotionByProduct(productId, today, today);

        if (promotion.isPresent()) {
            PromotionModel promo = promotion.get();
            if (promo.getType().equalsIgnoreCase("PERCENT")) {
                return originalPrice - (originalPrice * promo.getValue() / 100);
            } else if (promo.getType().equalsIgnoreCase("FIXED_VALUE")) {
                return Math.max(0, originalPrice - promo.getValue());
            }
        }

        return originalPrice;
    }

    //Get Promotion List
    @Override
    public List<PromotionModel> promotionActiveList() {
        LocalDate today = LocalDate.now();
        return repository.findActivePromotions(today, today);
    }

    //Get Promotion By ID
    @Override
    public Optional<PromotionModel> productFindById(String id) {
        return repository.findById(id);
    }

    //Save Promotion
    @Override
    public PromotionModel promotionSave(PromotionModel p) {

        if (p.getStartDate().isAfter(p.getEndDate())) {
            throw new IllegalArgumentException("A data de início deve ser antes da data de término.");
        }

        boolean hasConflict = repository.existsByProductIdAndEndDateAfter(p.getProductId(), p.getStartDate());
        if (hasConflict) {
            throw new IllegalArgumentException("Já existe uma promoção ativa para este produto nesse período.");
        }

        return repository.save(p);
    }

    //Delete Promotion
    @Override
    public void promotionDelete(String id) {
        repository.deleteById(id);
    }

}
