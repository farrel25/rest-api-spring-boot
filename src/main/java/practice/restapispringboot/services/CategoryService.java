package practice.restapispringboot.services;

import java.util.Optional;

import javax.transaction.TransactionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practice.restapispringboot.models.entities.Category;
import practice.restapispringboot.models.repositories.CategoryRepo;

@Service
@TransactionScoped
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public Category findOne(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        return !category.isPresent() ? null : category.get();
    }

    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    public void deleteOne(Long id) {
        categoryRepo.deleteById(id);
    }
    
}
