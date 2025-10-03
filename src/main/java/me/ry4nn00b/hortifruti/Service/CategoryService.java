package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.CategoryModel;
import me.ry4nn00b.hortifruti.Repository.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final ICategoryRepository repository;

    //Method's
    public CategoryService(ICategoryRepository repository) {
        this.repository = repository;
    }
    public List<CategoryModel> categoryList(){
        return repository.findAll();
    }
    public Optional<CategoryModel> categoryFindByID(String categoryId){
        return repository.findById(categoryId);
    }
    public CategoryModel categorySave(CategoryModel category){
        return repository.save(category);
    }
    public void categoryDelete(String id){
        repository.deleteById(id);
    }


}
