package me.ry4nn00b.hortifruti.Repository;

import me.ry4nn00b.hortifruti.Model.PromotionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPromotionRepository extends MongoRepository<PromotionModel, String> {

    //Promotions Active Between Dates
    @Query("{ 'startDate': { $lte: ?0 }, 'endDate': { $gte: ?1 } }")
    List<PromotionModel> findActivePromotions(LocalDate startDate, LocalDate endDate);

    //Active Promotions For a Product
    @Query("{ 'productId': ?0, 'startDate': { $lte: ?1 }, 'endDate': { $gte: ?2 } }")
    Optional<PromotionModel> findActivePromotionByProduct(String productId, LocalDate startDate, LocalDate endDate);

    boolean existsByProductIdAndEndDateAfter(String productId, LocalDate date);

}
