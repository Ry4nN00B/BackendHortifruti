package me.ry4nn00b.hortifruti.Controller;

import me.ry4nn00b.hortifruti.DTOs.StockRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.StockResponseDTO;
import me.ry4nn00b.hortifruti.Mapper.StockMapper;
import me.ry4nn00b.hortifruti.Model.StockModel;
import me.ry4nn00b.hortifruti.Service.Interface.IStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estoque")
public class StockController {

    private final IStockService stockService;

    public StockController(IStockService stockService) {
        this.stockService = stockService;
    }

    //ENDPOINT - Test API
    @GetMapping("/")
    public String home() {
        return "API DE ESTOQUE FUNCIONANDO!";
    }

    //ENDPOINT - List Stock
    @GetMapping
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<List<StockResponseDTO>> getAllStock() {

        List<StockResponseDTO> stockList = stockService.stockList();
        return ResponseEntity.ok(stockList);
    }

    //ENDPOINT - Get Stock By ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<StockResponseDTO> stockFindById(@PathVariable("id") String stockId) {

        return stockService.stockFindById(stockId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Get Stock By Product ID
    @GetMapping("/produto/{productId}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<List<StockResponseDTO>> stockByProduct(@PathVariable String productId) {

        List<StockResponseDTO> stockList = stockService.stockByProductId(productId);
        return ResponseEntity.ok(stockList);
    }

    //ENDPOINT - Create Stock
    @PostMapping
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<StockResponseDTO> createStock(@RequestBody StockRequestDTO requestDTO) {

        StockResponseDTO responseDTO = stockService.stockSave(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    //ENDPOINT - Delete Stock By ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<Void> stockDelete(@PathVariable("id") String stockId) {

        return stockService.stockFindById(stockId)
                .map(existing -> {
                    stockService.stockDelete(stockId);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Stock Near Expiration
    @GetMapping("/proximos-a-vencer")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<List<StockResponseDTO>> stockNearExpiration(@RequestParam int days) {

        List<StockResponseDTO> stockList = stockService.stockNearExpiration(days);
        return ResponseEntity.ok(stockList);
    }

    //ENDPOINT - Low Stock
    @GetMapping("/estoque-baixo")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<List<StockResponseDTO>> stockLowAmount(@RequestParam double minAmount) {

        List<StockResponseDTO> stockList = stockService.stockLowAmount(minAmount);
        return ResponseEntity.ok(stockList);
    }

    //ENDPOINT - Clean Empty Lots
    @DeleteMapping("/limpar-lotes-vazios")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'ESTOQUISTA')")
    public ResponseEntity<Void> cleanEmptyLots() {

        stockService.cleanEmptyLots();
        return ResponseEntity.noContent().build();
    }
}
