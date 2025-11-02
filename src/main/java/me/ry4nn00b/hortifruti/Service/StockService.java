package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.DTOs.StockRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.StockResponseDTO;
import me.ry4nn00b.hortifruti.Mapper.StockMapper;
import me.ry4nn00b.hortifruti.Model.StockModel;
import me.ry4nn00b.hortifruti.Repository.IStockRepository;
import me.ry4nn00b.hortifruti.Service.Interface.IStockService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService implements IStockService {

    private final IStockRepository repository;
    private final StockMapper stockMapper;

    public StockService(IStockRepository repository, StockMapper stockMapper) {
        this.repository = repository;
        this.stockMapper = stockMapper;
    }

    //Get Stock List
    @Override
    public List<StockResponseDTO> stockList() {
        return repository.findAll()
                .stream()
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //Get Stock By ID
    @Override
    public Optional<StockResponseDTO> stockFindById(String stockId) {
        return repository.findById(stockId)
                .map(stockMapper::toResponseDTO);
    }

    //Get Stock By Product ID
    @Override
    public List<StockResponseDTO> stockByProductId(String productId) {
        return repository.findAll().stream()
                .filter(s -> s.getProductId().equals(productId))
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //Save Stock
    @Override
    public StockResponseDTO stockSave(StockRequestDTO dto) {
        StockModel model = stockMapper.toModel(dto);
        model.setEntryDate(LocalDate.now());
        StockModel saved = repository.save(model);
        return stockMapper.toResponseDTO(saved);
    }

    //Delete By StockID
    @Override
    public void stockDelete(String stockId) {
        repository.deleteById(stockId);
    }

    //Get Stock With Expiration Date of Approximately X Days
    @Override
    public List<StockResponseDTO> stockNearExpiration(int daysBeforeExpire) {
        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(daysBeforeExpire);

        return repository.findAll().stream()
                .filter(s -> s.getValidity() != null &&
                        !s.getValidity().isBefore(today) &&
                        !s.getValidity().isAfter(limit))
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //Get Products With Low Stock
    @Override
    public List<StockResponseDTO> stockLowAmount(double minAmount) {
        return repository.findAll().stream()
                .filter(s -> s.getAmount() <= minAmount)
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //Remove Empty Lot's
    @Override
    public void cleanEmptyLots() {
        repository.findAll().stream()
                .filter(s -> s.getAmount() <= 0)
                .forEach(s -> repository.deleteById(s.getId()));
    }

}
