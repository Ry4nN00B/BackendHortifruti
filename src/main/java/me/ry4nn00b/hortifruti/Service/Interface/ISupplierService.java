package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.Model.SupplierModel;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {

    List<SupplierModel> supplierList();
    Optional<SupplierModel> supplierFindById(String supplierId);
    SupplierModel supplierSave(SupplierModel supplier);
    void supplierDelete(String id);

}
