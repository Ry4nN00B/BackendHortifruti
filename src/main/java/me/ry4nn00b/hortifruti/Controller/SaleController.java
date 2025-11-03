package me.ry4nn00b.hortifruti.Controller;

import jakarta.validation.Valid;
import me.ry4nn00b.hortifruti.DTOs.SaleRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.SaleResponseDTO;
import me.ry4nn00b.hortifruti.Service.Interface.ISaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vendas")
public class SaleController {

    private final ISaleService saleService;

    public SaleController(ISaleService saleService) {
        this.saleService = saleService;
    }

    //ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE VENDAS FUNCIONANDO!";
    }

    //ENDPOINT - List Sales
    @GetMapping
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<List<SaleResponseDTO>> saleList() {

        List<SaleResponseDTO> sales = saleService.saleList();
        return ResponseEntity.ok(sales);
    }

    //ENDPOINT - Find by ID Sale
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<SaleResponseDTO> findById(@PathVariable("id") String saleId) {

        return saleService.saleFindById(saleId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Create Sale
    @PostMapping
    @PreAuthorize("hasAnyAuthority('GERENTE', 'OPERADOR')")
    public ResponseEntity<?> saleRegister(@Valid @RequestBody SaleRequestDTO saleDTO) {
        try {
            SaleResponseDTO saved = saleService.saleRegister(saleDTO);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //ENDPOINT - Confirm Payment
    @PatchMapping("/{id}/confirmar")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'OPERADOR')")
    public ResponseEntity<?> confirmPayment(@PathVariable("id") String id) {
        try {
            SaleResponseDTO confirmed = saleService.confirmPayment(id);
            return ResponseEntity.ok(confirmed);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //ENDPOINT - Cancel Sale
    @PatchMapping("/{id}/cancelar")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'OPERADOR')")
    public ResponseEntity<?> cancelSale(@PathVariable("id") String id) {
        try {
            SaleResponseDTO cancelled = saleService.cancelSale(id);
            return ResponseEntity.ok(cancelled);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //ENDPOINT - Update Sale
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'OPERADOR')")
    public ResponseEntity<?> updateSale(@PathVariable("id") String id, @Valid @RequestBody SaleRequestDTO updateDTO) {

        return saleService.saleUpdate(id, updateDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Item Sale
    @DeleteMapping("/{saleId}/item/{productId}")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'OPERADOR')")
    public ResponseEntity<?> removeItemFromSale(@PathVariable String id, @PathVariable String productId) {

        return saleService.removeItemFromSale(id, productId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Complete Sale
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<?> delete(@PathVariable("id") String saleId) {

        saleService.saleDeleteId(saleId);
        return ResponseEntity.noContent().build();
    }

}
