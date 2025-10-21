package me.ry4nn00b.hortifruti.Controller;

import jakarta.validation.Valid;
import me.ry4nn00b.hortifruti.DTOs.SupplierRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.SupplierResponseDTO;
import me.ry4nn00b.hortifruti.Mapper.SupplierMapper;
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
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable String id, @Valid @RequestBody SupplierRequestDTO requestDTO) {

        return supplierService.supplierFindById(id)
                .map(existing -> {
                    if(requestDTO.getName() != null) existing.setName(requestDTO.getName());
                    if(requestDTO.getDescription() != null) existing.setDescription(requestDTO.getDescription());
                    if(requestDTO.getEmail() != null) existing.setEmail(requestDTO.getEmail());
                    if(requestDTO.getPhoneNumber() != null) existing.setPhoneNumber(requestDTO.getPhoneNumber());
                    if(requestDTO.getCnpj() != null) existing.setCnpj(requestDTO.getCnpj());

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
