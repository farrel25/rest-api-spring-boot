package practice.restapispringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practice.restapispringboot.models.entities.Product;
import practice.restapispringboot.services.ProductService;

@RestController // to mark this class as Rest Controller
@RequestMapping("/api/products") // to set up url endpoint
public class ProductController {

    // inject to its service class (Dependency Injection)
    @Autowired
    private ProductService productService;

    /**
     * because the data will be sent through request body
     * so we need to add annotation
     * @RequestBody
     * 
     * @param product
     * @return
     */
    @PostMapping // to mark this method as POST request
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping // to mark this method as GET request
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id) {
        return productService.findOne(id);
    }

    @PutMapping // to mark this method as PUT request
    public Product update(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        productService.deleteOne(id);;
    }
    
}
