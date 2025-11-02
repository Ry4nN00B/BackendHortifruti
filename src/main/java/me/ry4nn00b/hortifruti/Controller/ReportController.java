package me.ry4nn00b.hortifruti.Controller;

import me.ry4nn00b.hortifruti.DTOs.FinancialReportDTO;
import me.ry4nn00b.hortifruti.DTOs.HistoricalReportDTO;
import me.ry4nn00b.hortifruti.DTOs.StockReportDTO;
import me.ry4nn00b.hortifruti.DTOs.ValidityReportDTO;
import me.ry4nn00b.hortifruti.Service.Interface.IReportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class ReportController {

    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    //ENDPOINT - Test API
    @GetMapping("/")
    public String home() {
        return "API DE RELATÓRIOS FUNCIONANDO!";
    }

    //ENDPOINT - Validity Report
    @GetMapping("/vencendo")
    @PreAuthorize("hasAuthority('GERENTE')")
    public List<ValidityReportDTO> getExpirationReport() {
        return reportService.generateExpirationReport();
    }

    //ENDPOINT - Stock Report
    @GetMapping("/estoque")
    @PreAuthorize("hasAuthority('GERENTE')")
    public List<StockReportDTO> getStockReport() {
        return reportService.generateStockReport();
    }

    //ENDPOINT - Financial Report
    @GetMapping("/financeiro")
    @PreAuthorize("hasAuthority('GERENTE')")
    public List<FinancialReportDTO> getFinancialReport() {
        return reportService.generateFinancialReportFromSales();
    }

    //ENDPOINT - Historical Report
    @GetMapping("/historico")
    @PreAuthorize("hasAuthority('GERENTE')")
    public List<HistoricalReportDTO> getSalesHistoryReport() {
        return reportService.generateSalesHistoryReport();
    }
}
