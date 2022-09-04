package practice.restapispringboot.models.repositories;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import practice.restapispringboot.models.entities.Product;
import practice.restapispringboot.models.entities.Supplier;

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
    
    /**
     * spring has a feature called derivate function or query function
     * with that feature, spring can know what we want to query based on the name of the method we wrote
     * So it will automatically make query on the fly when we call the method
     * @param name
     * @return
     */
    List<Product> findByNameContains(String name);

    /**
     * JPA CUSTOM QUERY
     * 
     * @Query annotation doesn't use SQL, but JPAQL
     * it is similar to SQL but it's not SQL
     * FROM Product -> it is refers to the entity class, not the table name
     * WHERE p.name -> name refers to the properties of Product entity class
     * :name -> it is parameter to use in the query
     * 
     * @PathParam annotation is used to connect the parameter inside the query with the parameter inside method
     * 
     * @param name
     * @return
     */
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCategoryId(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);
}
