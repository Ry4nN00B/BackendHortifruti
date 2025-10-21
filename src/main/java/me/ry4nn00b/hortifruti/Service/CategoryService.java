package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.CategoryModel;
import me.ry4nn00b.hortifruti.Repository.ICategoryRepository;
import me.ry4nn00b.hortifruti.Service.Interface.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository repository;

    public CategoryService(ICategoryRepository repository) {
        this.repository = repository;
    }

    //Get Category List
    @Override
    public List<CategoryModel> categoryList(){
        return repository.findAll();
    }

    //Get Category By ID
    @Override
    public Optional<CategoryModel> categoryFindByID(String categoryId){
        return repository.findById(categoryId);
    }

    //Save Category
    @Override
    public CategoryModel categorySave(CategoryModel category){
        return repository.save(category);
    }

    //Delete Category
    @Override
    public void categoryDelete(String id){
        repository.deleteById(id);
    }


}
