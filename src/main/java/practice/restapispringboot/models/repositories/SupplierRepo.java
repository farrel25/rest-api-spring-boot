package practice.restapispringboot.models.repositories;

import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.PagingAndSortingRepository;

import practice.restapispringboot.models.entities.Supplier;

/**
 * PagingAndSortingRepository Interface
 * Extension of CrudRepository to provide additional methods to 
 * retrieve entities using the pagination and sorting abstraction.
 * 
 * JpaRepository Interface
 * The most complete repository
 * it has all method from CrudRepository and PagingAndSortingRepository
 * plus all specific JPA features
 */
// public interface SupplierRepo extends PagingAndSortingRepository<Supplier, Long>{
// public interface SupplierRepo extends JpaRepository<Supplier, Long>{
public interface SupplierRepo extends CrudRepository<Supplier, Long>{
    /**
     * Derived Query Method
     * Spring has a feature called derived query method
     * with that feature, spring can know what we want to query based on the name of the method we wrote
     * So it will automatically make query on the fly when we call the method
     * 
     * Docs:
     * https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#appendix.query.method.subject
     */
    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);
    
    List<Supplier> findByNameStartsWith(String prefix);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);
}
