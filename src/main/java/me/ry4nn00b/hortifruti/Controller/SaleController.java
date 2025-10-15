package me.ry4nn00b.hortifruti.Controller;

import me.ry4nn00b.hortifruti.Model.SaleModel;
import me.ry4nn00b.hortifruti.Service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    //ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE VENDAS FUNCIONANDO!";
    }

    //ENDPOINT - List Sales
    @GetMapping
    public List<SaleModel> saleList() {
        return saleService.saleList();
    }

    //ENDPOINT - Find by ID Sale
    @GetMapping("/{id}")
    public ResponseEntity<SaleModel> findById(@PathVariable("id") String saleId) {
        return saleService.saleFindById(saleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Create Sale
    @PostMapping
    public ResponseEntity<?> saleRegister(@RequestBody SaleModel sale) {
        try {
            SaleModel saved = saleService.saleRegister(sale);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //ENDPOINT - Update Sale
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSale(@PathVariable("id") String saleId, @RequestBody SaleModel updatedSale) {
        try {
            SaleModel updated = saleService.saleUpdate(saleId, updatedSale);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //ENDPOINT - Delete Complete Sale
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String saleId) {
        try {
            saleService.saleDeleteId(saleId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //ENDPOINT - Delete Item Sale
    @DeleteMapping("/{saleId}/item/{productId}")
    public ResponseEntity<?> removeItemFromSale(
            @PathVariable String saleId,
            @PathVariable String productId) {
        try {
            SaleModel updatedSale = saleService.removeItemFromSale(saleId, productId);
            return ResponseEntity.ok(updatedSale);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
