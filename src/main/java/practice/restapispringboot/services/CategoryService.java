package practice.restapispringboot.services;

import java.util.Optional;

import javax.transaction.TransactionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    
    public Iterable<Category> findByNameContains(String name, Pageable pageable) {
        return categoryRepo.findByNameContains(name, pageable);
        // return categoryRepo.findByNameContains(name, pageable).getContent();
    }

    // batch create
    public Iterable<Category> saveBatch(Iterable<Category> categories) {
        // this method usually used when upload data using csv/excel
        return categoryRepo.saveAll(categories);
    }
}
