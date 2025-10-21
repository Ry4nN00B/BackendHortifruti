package me.ry4nn00b.hortifruti.Controller;

import me.ry4nn00b.hortifruti.DTOs.StockRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.StockResponseDTO;
import me.ry4nn00b.hortifruti.Mapper.StockMapper;
import me.ry4nn00b.hortifruti.Model.StockModel;
import me.ry4nn00b.hortifruti.Service.Interface.IStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estoque")
public class StockController {

    private final IStockService stockService;
    private final StockMapper stockMapper;

    public StockController(IStockService stockService, StockMapper stockMapper) {
        this.stockService = stockService;
        this.stockMapper = stockMapper;
    }

    //ENDPOINT - Test API
    @GetMapping("/")
    public String home() {
        return "API DE ESTOQUE FUNCIONANDO!";
    }

    //ENDPOINT - Create Stock
    @PostMapping
    public ResponseEntity<StockResponseDTO> createStock(@RequestBody StockRequestDTO requestDTO) {

        StockModel model = stockMapper.toModel(requestDTO);
        StockModel saved = stockService.stockSave(model);
        StockResponseDTO responseDTO = stockMapper.toResponseDTO(saved);

        return ResponseEntity.ok(responseDTO);
    }

    //ENDPOINT - List Stock
    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> getAllStock() {
        List<StockModel> stock = stockService.stockList();
        List<StockResponseDTO> responseDTO = stock.stream().map(stockMapper::toResponseDTO).toList();

        return ResponseEntity.ok(responseDTO);
    }

    //ENDPOINT - Get Stock By ID
    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDTO> stockFindById(@PathVariable("id") String stockId) {
        return stockService.stockFindById(stockId)
                .map(stockMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Get Stock By Product ID
    @GetMapping("/produto/{productId}")
    public List<StockResponseDTO> stockByProduct(@PathVariable String productId) {
        return stockService.stockByProductId(productId)
                .stream()
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //ENDPOINT - Delete Stock By ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> stockDelete(@PathVariable("id") String stockId) {
        return stockService.stockFindById(stockId)
                .map(existing -> {
                    stockService.stockDelete(stockId);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Stock Near Expiration
    @GetMapping("/proximos-a-vencer")
    public List<StockResponseDTO> stockNearExpiration(@RequestParam int dias) {
        return stockService.stockNearExpiration(dias)
                .stream()
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //ENDPOINT - Low Stock
    @GetMapping("/estoque-baixo")
    public List<StockResponseDTO> stockLowAmount(@RequestParam double minAmount) {
        return stockService.stockLowAmount(minAmount)
                .stream()
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //ENDPOINT - Clean Empty Lots
    @DeleteMapping("/limpar-lotes-vazios")
    public ResponseEntity<?> cleanEmptyLots() {
        stockService.cleanEmptyLots();
        return ResponseEntity.noContent().build();
    }
}
