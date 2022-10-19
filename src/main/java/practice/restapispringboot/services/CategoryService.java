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

        /**
         * kondisi untuk mengecek apakah data category yg diinput sudah ada id nya atau belum.
         * kalau sudah ada id nya, berarti merupakan request update.
         * supaya bisa mendapatkan data createdDate & createdBy nya dan supaya 2 field itu
         * tidak berubah nilainya jadi null saat kita menjalankan method save dari categoryRepo, kita
         * perlu ambil data nya sesuai id dari tabel dahulu, kemudian field/property name
         * (field yg diupdate) nya di set pakai yg baru. Setelah itu kita timpa data category
         * yg baru dengan currentCategory
         */
        if (category.getId() != null) {
            Category currentCategory = categoryRepo.findById(category.getId()).get();
            currentCategory.setName(category.getName());
            category = currentCategory;
        }
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
