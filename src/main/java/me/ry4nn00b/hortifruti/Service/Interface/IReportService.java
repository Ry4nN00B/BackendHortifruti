package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.DTOs.FinancialReportDTO;
import me.ry4nn00b.hortifruti.DTOs.HistoricalReportDTO;
import me.ry4nn00b.hortifruti.DTOs.StockReportDTO;
import me.ry4nn00b.hortifruti.DTOs.ValidityReportDTO;

import java.util.List;

public interface IReportService {

    List<ValidityReportDTO> generateExpirationReport();
    List<StockReportDTO> generateStockReport();
    List<FinancialReportDTO> generateFinancialReportFromSales();
    List<HistoricalReportDTO> generateSalesHistoryReport();

}
