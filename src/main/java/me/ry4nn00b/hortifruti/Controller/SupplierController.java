package me.ry4nn00b.hortifruti.Controller;

import me.ry4nn00b.hortifruti.Model.SupplierModel;
import me.ry4nn00b.hortifruti.Service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    //ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE FORNCECEDORES FUNCIONANDO!";
    }

    //ENDPOINT - Create Supplier
    @PostMapping
    public ResponseEntity<SupplierModel> createSupplier(@RequestBody SupplierModel supplier) {
        SupplierModel saved = supplierService.supplierSave(supplier);
        return ResponseEntity.ok(saved);
    }

    //ENDPOINT - List Supplier
    @GetMapping
    public ResponseEntity<List<SupplierModel>> getAllSuppliers() {
        List<SupplierModel> suppliers = supplierService.supplierList();
        return ResponseEntity.ok(suppliers);
    }

    //ENDPOINT - Find By ID Supplier
    @GetMapping("/{id}")
    public ResponseEntity<SupplierModel> getSupplierById(@PathVariable String id) {
        return supplierService.supplierFindById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Update Supplier
    public ResponseEntity<SupplierModel> updateSupplier(@PathVariable String id, @RequestBody SupplierModel newSupplier) {
        return supplierService.supplierFindById(id)
                .map(supplier -> {
                    supplier.setName(newSupplier.getName());
                    supplier.setDescription(newSupplier.getDescription());
                    supplier.setEmail(newSupplier.getEmail());
                    supplier.setPhoneNumber(newSupplier.getPhoneNumber());
                    supplier.setCnpj(newSupplier.getCnpj());
                    SupplierModel updated = supplierService.supplierSave(supplier);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Supplier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable String id) {
        if (supplierService.supplierFindById(id).equals(id)) {
            supplierService.supplierDelete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
