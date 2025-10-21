package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.SupplierModel;
import me.ry4nn00b.hortifruti.Repository.ISupplierRepository;
import me.ry4nn00b.hortifruti.Service.Interface.ISupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements ISupplierService {

    private final ISupplierRepository repository;

    public SupplierService(ISupplierRepository repository) {
        this.repository = repository;
    }

    //Get Supplier List
    @Override
    public List<SupplierModel> supplierList() {
        return repository.findAll();
    }

    //Get Supplier By ID
    @Override
    public Optional<SupplierModel> supplierFindById(String supplierId){
        return repository.findById(supplierId);
    }

    //Save Supplier
    @Override
    public SupplierModel supplierSave(SupplierModel supplier) {
        return repository.save(supplier);
    }

    //Delete Supplier
    @Override
    public void supplierDelete(String id) {
        repository.deleteById(id);
    }

}
