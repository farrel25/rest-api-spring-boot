package practice.restapispringboot.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import practice.restapispringboot.models.entities.Category;

/**
 * {@link PagingAndSortingRepository} Interface
 * Extension of CrudRepository to provide additional methods to 
 * retrieve entities using the pagination and sorting abstraction.
 * 
 * {@link JpaRepository} Interface
 * The most complete repository.
 * it has all method from CrudRepository and PagingAndSortingRepository,
 * plus all specific JPA features
 */
// public interface CategoryRepo extends CrudRepository<Category, Long>{
public interface CategoryRepo extends PagingAndSortingRepository<Category, Long>{
    
    /**
     * @object Pageable
     * used to set up or configure paging and sorting
     * 
     * @return Page
     */
    // Derived Query Method
    Page<Category> findByNameContains(String name, Pageable pageable);
}
