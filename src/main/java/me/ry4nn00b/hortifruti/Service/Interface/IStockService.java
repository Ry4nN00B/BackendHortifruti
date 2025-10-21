package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.Model.StockModel;

import java.util.List;
import java.util.Optional;

public interface IStockService {

    List<StockModel> stockList();
    Optional<StockModel> stockFindById(String stockId);
    List<StockModel> stockByProductId(String productId);
    StockModel stockSave(StockModel stock);
    void stockDelete(String stockId);
    List<StockModel> stockNearExpiration(int daysBeforeExpire);
    List<StockModel> stockLowAmount(double minAmount);
    void cleanEmptyLots();

}
