package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.DTOs.CategoryRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.CategoryResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.CategoryUpdateDTO;
import me.ry4nn00b.hortifruti.Mapper.CategoryMapper;
import me.ry4nn00b.hortifruti.Model.CategoryModel;
import me.ry4nn00b.hortifruti.Repository.ICategoryRepository;
import me.ry4nn00b.hortifruti.Service.Interface.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryService(ICategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //Get Category List
    @Override
    public List<CategoryResponseDTO> categoryList() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    //Get Category By ID
    @Override
    public Optional<CategoryResponseDTO> categoryFindByID(String categoryId) {
        return repository.findById(categoryId)
                .map(mapper::toResponseDTO);
    }

    //Save Category
    @Override
    public CategoryResponseDTO categorySave(CategoryRequestDTO categoryDTO) {
        CategoryModel category = mapper.toModel(categoryDTO);
        CategoryModel saved = repository.save(category);
        return mapper.toResponseDTO(saved);
    }

    //Update Category
    @Override
    public Optional<CategoryResponseDTO> categoryUpdate(String id, CategoryUpdateDTO updateDTO) {
        return Optional.ofNullable(repository.findById(id).map(existing -> {

            if (updateDTO.getName() != null) existing.setName(updateDTO.getName());
            if (updateDTO.getDescription() != null) existing.setDescription(updateDTO.getDescription());

            CategoryModel updated = repository.save(existing);
            return mapper.toResponseDTO(updated);
        }).orElseThrow(() -> new IllegalArgumentException("Hortifruti Erro: Categoria não encontrada. ID: " + id)));
    }

    //Delete Category
    @Override
    public void categoryDelete(String id) {
        repository.deleteById(id);
    }

}
