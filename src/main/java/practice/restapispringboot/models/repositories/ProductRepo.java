package practice.restapispringboot.models.repositories;

import org.springframework.data.repository.CrudRepository;

import practice.restapispringboot.models.entities.Product;

/**
 * this repository interface is just like DAO (Data Access Object)
 * consist some methods to manipulate table data, as simple as Create, Update, Delete, etc
 * 
 * CrudRepository parent class
 * by extending CrudRepository class to this interface
 * this interface will automatically have ability to proccess CRUD without explicitly code inside this interface
 * it need 2 argument by implementing generic technique, there are entity and its primary id data type
 */
public interface ProductRepo extends CrudRepository<Product, Long>{
    
}
