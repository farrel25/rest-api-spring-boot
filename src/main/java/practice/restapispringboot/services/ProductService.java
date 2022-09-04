package practice.restapispringboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practice.restapispringboot.models.entities.Product;
import practice.restapispringboot.models.entities.Supplier;
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

    @Autowired
    private SupplierService supplierService;

    // CREATE and UPDATE can use save method from productRepo
    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findOne(Long id) {
        Optional<Product> product = productRepo.findById(id);
        
        // if (!product.isPresent()) {
        //     return null;
        // }
        // return productRepo.findById(id).get();

        return !product.isPresent() ? null:product.get();
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public Boolean deleteOne(Long id) {
        // productRepo.deleteById(id);
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent()) {
            return false;
        }
        productRepo.deleteById(id);
        return true;
    }

    public List<Product> findByName(String name) {
        return productRepo.findByNameContains(name);
    }

    public void addSupplier(Supplier supplier, Long productId) {
        Product product = findOne(productId);
        if (product == null) {
            throw new RuntimeException("Product with id: " + productId + " not found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }

    // Custom query implementation
    public Product findByProductName(String name) {
        return productRepo.findProductByName(name);
    }

    public List<Product> findByProductNameLike(String name) {
        return productRepo.findProductByNameLike("%" + name + "%");
    }
    
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepo.findProductByCategoryId(categoryId);
    }

    public List<Product> findBySupplier(Long supplierId) {
        Supplier supplier = supplierService.findOne(supplierId);
        if (supplier == null) {
            return new ArrayList<>();
        }
        return productRepo.findProductBySupplier(supplier);
    };
}
