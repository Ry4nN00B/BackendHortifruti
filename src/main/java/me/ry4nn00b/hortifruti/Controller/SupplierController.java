package me.ry4nn00b.hortifruti.Controller;

import jakarta.validation.Valid;
import me.ry4nn00b.hortifruti.DTOs.SupplierRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.SupplierResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.SupplierUpdateDTO;
import me.ry4nn00b.hortifruti.Service.Interface.ISupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class SupplierController {

    private final ISupplierService supplierService;

    public SupplierController(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    //ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE FORNCECEDORES FUNCIONANDO!";
    }

    //ENDPOINT - List Supplier
    @GetMapping
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {

        List<SupplierResponseDTO> suppliers = supplierService.supplierList();
        return ResponseEntity.ok(suppliers);
    }

    //ENDPOINT - Find By ID Supplier
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable String id) {

        return supplierService.supplierFindById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Create Supplier
    @PostMapping
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<SupplierResponseDTO> createSupplier(@Valid @RequestBody SupplierRequestDTO requestDTO) {

        SupplierResponseDTO responseDTO = supplierService.supplierSave(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    //ENDPOINT - Update Supplier
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable String id, @Valid @RequestBody SupplierUpdateDTO updateDTO) {

        return supplierService.supplierUpdate(id, updateDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Supplier
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<Void> deleteSupplier(@PathVariable String id) {

        supplierService.supplierDelete(id);
        return ResponseEntity.noContent().build();
    }
}
