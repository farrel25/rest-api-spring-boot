package practice.restapispringboot.models.repositories;

import org.springframework.data.repository.CrudRepository;

import practice.restapispringboot.models.entities.Category;

public interface CategoryRepo extends CrudRepository<Category, Long>{
    
}
