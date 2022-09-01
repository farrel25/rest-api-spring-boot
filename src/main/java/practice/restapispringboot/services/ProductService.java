package practice.restapispringboot.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practice.restapispringboot.models.entities.Product;
import practice.restapispringboot.models.repositories.ProductRepo;

/**
 * usually, service classes are consist of business logic/proccess 
 */
@Service // to mark this class as service
@Transactional
public class ProductService {

    // inject to its repository interface (Dependency Injection)
    @Autowired
    private ProductRepo productRepo; // repository object with ProductRepo data type

    // CREATE and UPDATE can use save method from productRepo
    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findOne(Long id) {
        return productRepo.findById(id).get();
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public void deleteOne(Long id) {
        productRepo.deleteById(id);
    }

    public List<Product> findByName(String name) {
        return productRepo.findByNameContains(name);
    }
    
}
