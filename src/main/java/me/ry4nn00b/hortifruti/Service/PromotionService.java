package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.PromotionModel;
import me.ry4nn00b.hortifruti.Repository.IPromotionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    private final IPromotionRepository repository;

    public PromotionService(IPromotionRepository repository) {
        this.repository = repository;
    }

    //Get Promotion List
    public List<PromotionModel> promotionActiveList() {
        return repository.findByEndDate(LocalDate.now());
    }

    //Get Promotion By ID
    public Optional<PromotionModel> productFindById(String id) {
        return repository.findById(id);
    }

    //Save Promotion
    public PromotionModel promotionSave(PromotionModel p) {
        return repository.save(p);
    }

    //Delete Promotion
    public void promotionDelete(String id) {
        repository.deleteById(id);
    }

}
