package me.ry4nn00b.hortifruti.Controller;

import jakarta.validation.Valid;
import me.ry4nn00b.hortifruti.Model.DTOs.SupplierRequestDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.SupplierResponseDTO;
import me.ry4nn00b.hortifruti.Mapper.SupplierMapper;
import me.ry4nn00b.hortifruti.Model.DTOs.SupplierUpdateDTO;
import me.ry4nn00b.hortifruti.Model.SupplierModel;
import me.ry4nn00b.hortifruti.Service.Interface.ISupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class SupplierController {

    private final ISupplierService supplierService;
    private final SupplierMapper supplierMapper;

    public SupplierController(ISupplierService supplierService, SupplierMapper supplierMapper) {
        this.supplierService = supplierService;
        this.supplierMapper = supplierMapper;
    }

    //ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE FORNCECEDORES FUNCIONANDO!";
    }

    //ENDPOINT - Create Supplier
    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@Valid @RequestBody SupplierRequestDTO requestDTO) {

        SupplierModel model = supplierMapper.toModel(requestDTO);
        SupplierModel saved = supplierService.supplierSave(model);
        SupplierResponseDTO responseDTO = supplierMapper.toResponseDTO(saved);

        return ResponseEntity.ok(responseDTO);
    }

    //ENDPOINT - List Supplier
    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        List<SupplierModel> supplier = supplierService.supplierList();
        List<SupplierResponseDTO> responseDTOs = supplier.stream().map(supplierMapper::toResponseDTO).toList();

        return ResponseEntity.ok(responseDTOs);
    }

    //ENDPOINT - Find By ID Supplier
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable String id) {
        return supplierService.supplierFindById(id)
                .map(supplierMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Update Supplier
    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable String id, @Valid @RequestBody SupplierUpdateDTO updateDTO) {

        return supplierService.supplierFindById(id)
                .map(existing -> {
                    if(updateDTO.getName() != null) existing.setName(updateDTO.getName());
                    if(updateDTO.getDescription() != null) existing.setDescription(updateDTO.getDescription());
                    if(updateDTO.getEmail() != null) existing.setEmail(updateDTO.getEmail());
                    if(updateDTO.getPhoneNumber() != null) existing.setPhoneNumber(updateDTO.getPhoneNumber());
                    if(updateDTO.getCnpj() != null) existing.setCnpj(updateDTO.getCnpj());

                    SupplierModel updated = supplierService.supplierSave(existing);
                    SupplierResponseDTO responseDTO = supplierMapper.toResponseDTO(updated);

                    return ResponseEntity.ok(responseDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete Supplier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable String id) {
        return supplierService.supplierFindById(id)
                .map(existing -> {
                    supplierService.supplierDelete(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
