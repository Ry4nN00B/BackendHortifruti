package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.DTOs.SupplierRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.SupplierResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.SupplierUpdateDTO;
import me.ry4nn00b.hortifruti.Model.SupplierModel;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {

    List<SupplierResponseDTO> supplierList();
    Optional<SupplierResponseDTO> supplierFindById(String supplierId);
    SupplierResponseDTO supplierSave(SupplierRequestDTO requestDTO);
    Optional<SupplierResponseDTO> supplierUpdate(String supplierId, SupplierUpdateDTO updateDTO);
    void supplierDelete(String id);

}
