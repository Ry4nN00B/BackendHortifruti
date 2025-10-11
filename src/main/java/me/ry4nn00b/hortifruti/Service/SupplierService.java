package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.CategoryModel;
import me.ry4nn00b.hortifruti.Model.SupplierModel;
import me.ry4nn00b.hortifruti.Repository.ISupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final ISupplierRepository repository;

    //Method's
    public SupplierService(ISupplierRepository repository) {
        this.repository = repository;
    }

    public List<SupplierModel> supplierList() {
        return repository.findAll();
    }

    public Optional<SupplierModel> supplierFindById(String supplierId){
        return repository.findById(supplierId);
    }

    public SupplierModel supplierSave(SupplierModel supplier) {
        return repository.save(supplier);
    }

    public void supplierDelete(String id) {
        repository.deleteById(id);
    }

}
