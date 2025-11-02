package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.DTOs.FinancialReportDTO;
import me.ry4nn00b.hortifruti.DTOs.HistoricalReportDTO;
import me.ry4nn00b.hortifruti.DTOs.StockReportDTO;
import me.ry4nn00b.hortifruti.DTOs.ValidityReportDTO;
import me.ry4nn00b.hortifruti.Model.ProductModel;
import me.ry4nn00b.hortifruti.Repository.IProductRepository;
import me.ry4nn00b.hortifruti.Repository.ISaleRepository;
import me.ry4nn00b.hortifruti.Repository.IStockRepository;
import me.ry4nn00b.hortifruti.Service.Interface.IReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService implements IReportService {

    private final IStockRepository stockRepository;
    private final IProductRepository productRepository;
    private final ISaleRepository saleRepository;

    public ReportService(IStockRepository stockRepository, IProductRepository productRepository, ISaleRepository saleRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    //Validity Report
    @Override
    public List<ValidityReportDTO> generateExpirationReport() {
        LocalDate today = LocalDate.now();

        return stockRepository.findAll().stream()
                .map(stock -> {
                    Optional<ProductModel> productOpt = productRepository.findById(stock.getProductId());
                    String productName = productOpt.map(ProductModel::getName).orElse("Desconhecido");

                    String status;
                    if (stock.getValidity() == null) {
                        status = "Sem data de validade";
                    } else if (stock.getValidity().isBefore(today)) {
                        status = "Vencido";
                    } else if (!stock.getValidity().isAfter(today.plusDays(3))) {
                        status = "Próximo do vencimento";
                    } else {
                        status = "Dentro do prazo";
                    }

                    return new ValidityReportDTO(productName, stock.getAmount(), stock.getValidity(), status);

                }).toList();
    }

    //Stock Report
    @Override
    public List<StockReportDTO> generateStockReport() {
        LocalDate today = LocalDate.now();

        return stockRepository.findAll().stream()
                .map(stock -> {
                    Optional<ProductModel> productOpt = productRepository.findById(stock.getProductId());
                    String productName = productOpt.map(ProductModel::getName).orElse("Desconhecido");

                    Long daysRemaining = null;
                    if (stock.getValidity() != null) {
                        daysRemaining = ChronoUnit.DAYS.between(today, stock.getValidity());
                    }

                    return new StockReportDTO(productName, stock.getAmount(), stock.getEntryDate(), stock.getValidity(), daysRemaining);
                }).toList();
    }

    //Financial Report
    @Override
    public List<FinancialReportDTO> generateFinancialReportFromSales() {
        return saleRepository.findAll().stream()
                .filter(sale -> sale.getItems() != null && !sale.getItems().isEmpty())
                .flatMap(sale -> sale.getItems().stream()
                        .map(item -> {
                            String productName = productRepository.findById(item.getProductId())
                                    .map(ProductModel::getName)
                                    .orElse("Desconhecido");

                            BigDecimal price = item.getUnitPrice().setScale(2, RoundingMode.HALF_UP);
                            BigDecimal total = price.multiply(BigDecimal.valueOf(item.getAmount())).setScale(2, RoundingMode.HALF_UP);

                            return new FinancialReportDTO(productName, item.getAmount(), price, total);
                        })
                ).toList();
    }

    //Historical Report
    @Override
    public List<HistoricalReportDTO> generateSalesHistoryReport() {
        return saleRepository.findAll().stream()
                .filter(sale -> sale.getItems() != null && !sale.getItems().isEmpty())
                .flatMap(sale -> sale.getItems().stream()
                        .map(item -> {
                            String productName = productRepository.findById(item.getProductId())
                                    .map(ProductModel::getName)
                                    .orElse("Desconhecido");

                            return new HistoricalReportDTO(
                                    productName,
                                    sale.getStatus().name(),
                                    item.getAmount(),
                                    sale.getDateTime(),
                                    "Nenhuma"
                            );
                        })
                ).toList();
    }

}
