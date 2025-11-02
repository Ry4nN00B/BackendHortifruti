package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.DTOs.SupplierRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.SupplierResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.SupplierUpdateDTO;
import me.ry4nn00b.hortifruti.Mapper.SupplierMapper;
import me.ry4nn00b.hortifruti.Model.CategoryModel;
import me.ry4nn00b.hortifruti.Model.SupplierModel;
import me.ry4nn00b.hortifruti.Repository.ISupplierRepository;
import me.ry4nn00b.hortifruti.Service.Interface.ISupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierService implements ISupplierService {

    private final ISupplierRepository repository;
    private final SupplierMapper supplierMapper;

    public SupplierService(ISupplierRepository repository, SupplierMapper supplierMapper) {
        this.repository = repository;
        this.supplierMapper = supplierMapper;
    }

    //Get Supplier List
    @Override
    public List<SupplierResponseDTO> supplierList() {
        return repository.findAll()
                .stream()
                .map(supplierMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //Get Supplier By ID
    @Override
    public Optional<SupplierResponseDTO> supplierFindById(String supplierId) {
        return repository.findById(supplierId)
                .map(supplierMapper::toResponseDTO);
    }

    //Save Supplier
    @Override
    public SupplierResponseDTO supplierSave(SupplierRequestDTO requestDTO) {
        SupplierModel model = supplierMapper.toModel(requestDTO);
        SupplierModel saved = repository.save(model);
        return supplierMapper.toResponseDTO(saved);
    }

    //Update Supplier
    @Override
    public Optional<SupplierResponseDTO> supplierUpdate(String id, SupplierUpdateDTO updateDTO) {
        return Optional.ofNullable(repository.findById(id).map(existing -> {

            if (updateDTO.getName() != null) existing.setName(updateDTO.getName());
            if (updateDTO.getDescription() != null) existing.setDescription(updateDTO.getDescription());
            if (updateDTO.getPhoneNumber() != null) existing.setPhoneNumber(updateDTO.getPhoneNumber());
            if (updateDTO.getEmail() != null) existing.setEmail(updateDTO.getEmail());
            if (updateDTO.getCnpj() != null) existing.setCnpj(updateDTO.getCnpj());

            SupplierModel updated = repository.save(existing);
            return supplierMapper.toResponseDTO(updated);
        }).orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Fornecedor não encontrada. ID: " + id)));
    }

    //Delete Supplier
    @Override
    public void supplierDelete(String supplierId) {
        repository.deleteById(supplierId);
    }

}
