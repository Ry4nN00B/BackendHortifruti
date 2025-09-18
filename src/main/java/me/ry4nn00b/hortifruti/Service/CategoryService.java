package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Model.CategoryModel;
import me.ry4nn00b.hortifruti.Repository.ICategoryRepository;

import java.util.List;

public class CategoryService {

    private final ICategoryRepository repository;

    //Method's
    public CategoryService(ICategoryRepository repository) {
        this.repository = repository;
    }
    public List<CategoryModel> categoryList(){
        return repository.findAll();
    }
    public CategoryModel categorySave(CategoryModel category){
        return repository.save(category);
    }
    public void categoryDelete(String id){
        repository.deleteById(id);
    }


}
