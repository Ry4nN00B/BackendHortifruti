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

    @GetMapping
    public List<SaleModel> saleList() {
        return saleService.saleList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleModel> findById(@PathVariable String saleId) {
        return saleService.saleFindById(saleId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Sale Register
    @PostMapping
    public ResponseEntity<?> saleRegister(@RequestBody SaleModel sale) {
        try {
            SaleModel saved = saleService.saleRegister(sale);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String saleId) {
        saleService.saleDeleteId(saleId);
        return ResponseEntity.noContent().build();
    }

}
