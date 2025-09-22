package me.ry4nn00b.hortifruti.Repository;

import me.ry4nn00b.hortifruti.Model.PromotionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPromotionRepository extends MongoRepository<PromotionModel, String> {
    List<PromotionModel> findByStartDate(LocalDate date);
    List<PromotionModel> findByEndDate(LocalDate date);
}
