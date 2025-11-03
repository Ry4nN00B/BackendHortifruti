package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.DTOs.StockRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.StockResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IStockService {

    List<StockResponseDTO> stockList();
    Optional<StockResponseDTO> stockFindById(String stockId);
    List<StockResponseDTO> stockByProductId(String productId);
    StockResponseDTO stockSave(StockRequestDTO dto);
    void stockDelete(String stockId);
    List<StockResponseDTO> stockNearExpiration(int daysBeforeExpire);
    List<StockResponseDTO> stockLowAmount(double minAmount);
    void cleanEmptyLots();

}
