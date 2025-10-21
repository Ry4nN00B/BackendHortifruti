package me.ry4nn00b.hortifruti.Repository;

import me.ry4nn00b.hortifruti.Model.StockModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IStockRepository extends MongoRepository<StockModel, String> {
    List<StockModel> findByProductId(String productId);
    List<StockModel> findByValidityBefore(LocalDate date);
    List<StockModel> findByValidityBetween(LocalDate start, LocalDate end);
}
