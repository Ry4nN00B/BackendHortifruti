package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.PromotionModel;
import me.ry4nn00b.hortifruti.Repository.IPromotionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionService {

    private final IPromotionRepository repository;

    //Method's
    public PromotionService(IPromotionRepository repository) {
        this.repository = repository;
    }

    public List<PromotionModel> promotionActiveList() {
        return repository.findByEndDate(LocalDate.now());
    }

    public PromotionModel promotionSave(PromotionModel p) {
        return repository.save(p);
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }

}
