package me.ry4nn00b.hortifruti.Repository;

import me.ry4nn00b.hortifruti.Model.PromotionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPromotionRepository extends MongoRepository<PromotionModel, String> {

    List<PromotionModel> findByStartAndEndDate(LocalDate startDate, LocalDate endDate);
    Optional<PromotionModel> findByProductIdStartAndEndDate(String productId, LocalDate start, LocalDate end);
    boolean existsByProductIdAndEndDateAfter(String productId, LocalDate date);

}
