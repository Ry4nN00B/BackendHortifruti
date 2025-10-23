package me.ry4nn00b.hortifruti.Service;

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

    public StockService(IStockRepository repository) {
        this.repository = repository;
    }

    //Get Stock List
    @Override
    public List<StockModel> stockList() {
        return repository.findAll();
    }

    //Get Stock By ID
    @Override
    public Optional<StockModel> stockFindById(String stockId) {
        return repository.findById(stockId);
    }

    //Get Stock By Product ID
    @Override
    public List<StockModel> stockByProductId(String productId) {
        return repository.findAll()
                .stream()
                .filter(s -> s.getProductId().equals(productId))
                .collect(Collectors.toList());
    }

    //Save Stock
    @Override
    public StockModel stockSave(StockModel stock) {
        stock.setEntryDate(LocalDate.now());
        return repository.save(stock);
    }

    //Delete By StockID
    @Override
    public void stockDelete(String stockId) {
        repository.deleteById(stockId);
    }

    //Get Stock With Expiration Date of Approximately X Days
    @Override
    public List<StockModel> stockNearExpiration(int daysBeforeExpire) {
        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(daysBeforeExpire);

        return repository.findAll()
                .stream()
                .filter(s -> s.getValidity() != null &&
                        !s.getValidity().isBefore(today) &&
                        !s.getValidity().isAfter(limit))
                .collect(Collectors.toList());
    }

    //Get Products With Low Stock
    @Override
    public List<StockModel> stockLowAmount(double minAmount) {
        return repository.findAll()
                .stream()
                .filter(s -> s.getAmount() <= minAmount)
                .collect(Collectors.toList());
    }

    //Remove Empty Lot's
    @Override
    public void cleanEmptyLots() {
        List<StockModel> all = repository.findAll();
        all.stream()
                .filter(s -> s.getAmount() <= 0)
                .forEach(s -> repository.deleteById(s.getId()));
    }

}
