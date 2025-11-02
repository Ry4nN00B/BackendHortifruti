package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.DTOs.*;
import me.ry4nn00b.hortifruti.Mapper.ProductMapper;
import me.ry4nn00b.hortifruti.Model.ProductModel;
import me.ry4nn00b.hortifruti.Repository.ICategoryRepository;
import me.ry4nn00b.hortifruti.Repository.IProductRepository;
import me.ry4nn00b.hortifruti.Repository.ISupplierRepository;
import me.ry4nn00b.hortifruti.Service.Interface.IProductService;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final IProductRepository repository;
    private final ICategoryRepository categoryRepository;
    private final ISupplierRepository supplierRepository;
    private final ProductMapper mapper;

    public ProductService(IProductRepository repository, ICategoryRepository categoryRepository, ISupplierRepository supplierRepository, ProductMapper mapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
    }

    //Get Product List
    @Override
    public List<ProductResponseDTO> productList() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    //Get Product By ID
    @Override
    public Optional<ProductResponseDTO> productFindById(String id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO);
    }

    //Save Product
    @Override
    public ProductResponseDTO productSave(ProductRequestDTO requestDTO) {
        validateCategoryAndSupplier(requestDTO.getCategoryId(), requestDTO.getSupplierId());

        ProductModel model = mapper.toModel(requestDTO);
        model.setPrice(model.getPrice().setScale(2, RoundingMode.HALF_UP));

        ProductModel saved = repository.save(model);
        return mapper.toResponseDTO(saved);
    }

    //Update Product
    @Override
    public Optional<ProductResponseDTO> productUpdate(String id, ProductUpdateDTO updateDTO) {
        return Optional.ofNullable(repository.findById(id).map(existing -> {

            if (updateDTO.getName() != null) existing.setName(updateDTO.getName());
            if (updateDTO.getDescription() != null) existing.setDescription(updateDTO.getDescription());
            if (updateDTO.getPrice() != null) existing.setPrice(updateDTO.getPrice().setScale(2, RoundingMode.HALF_UP));
            if (updateDTO.getCategoryId() != null) {
                categoryRepository.findById(updateDTO.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Hortifruti Erro: Categoria não encontrada."));
                existing.setCategoryId(updateDTO.getCategoryId());
            }
            if (updateDTO.getSupplierId() != null) {
                supplierRepository.findById(updateDTO.getSupplierId())
                        .orElseThrow(() -> new RuntimeException("Hortifruti Erro: Fornecedor não encontrado."));
                existing.setSupplierId(updateDTO.getSupplierId());
            }
            if (updateDTO.getSoldByWeight() != null) existing.setSoldByWeight(updateDTO.getSoldByWeight());

            ProductModel updated = repository.save(existing);
            return mapper.toResponseDTO(updated);
        }).orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Produto não encontrada. ID: " + id)));

    }

    //Delete Product
    @Override
    public void productDelete(String id){
        repository.deleteById(id);
    }

    //Validate Category And Supplier Exists
    private void validateCategoryAndSupplier(String categoryId, String supplierId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Hortifruti Erro: Categoria não encontrada."));

        supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Hortifruti Erro: Fornecedor não encontrado."));
    }

}
