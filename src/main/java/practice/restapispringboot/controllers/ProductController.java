package practice.restapispringboot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practice.restapispringboot.dto.ResponseData;
import practice.restapispringboot.dto.SearchData;
import practice.restapispringboot.models.entities.Product;
import practice.restapispringboot.models.entities.Supplier;
import practice.restapispringboot.services.ProductService;

@RestController // to mark this class as Rest Controller
@RequestMapping("/api/products") // to set up url endpoint
public class ProductController {

    // inject to its service class (Dependency Injection)
    @Autowired
    private ProductService productService;

    /**
     * because the data will be sent through request body
     * so we need to add annotation @RequestBody
     * 
     * we need to tell the controller if there are some data need to validate first
     * it can be done by using @Valid
     * the data will be validated based on validation annotation in entity class
     * if the data is not valid, then error object will be generated
     * so we need to add second parameter with -> Errors errors
     * 
     * because the return value will be formatted as json that consist error http response or success http response or etc
     * then the data type can't be Product anymore
     * so, the data type used by this method changed to ResponseEntity that encapsulated using ResponseData (dto).
     * Because ResponseData is generic class, so it can return any object
     * in this case, it will return Product
     * 
     * @param product
     * @return
     */
    @PostMapping // to mark this method as POST request
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                // System.err.println(error.getDefaultMessage()); // print error message in terminal

                // error message will be stored in messages property that belongs to ResponseData object
                responseData.getMessages().add(error.getDefaultMessage());
            }
            // throw new RuntimeException("Validation error"); // throw a RuntimeException as a response

            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        // return productService.save(product);

        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));

        // return ResponseEntity.status(HttpStatus.OK).body(responseData);
        return ResponseEntity.ok(responseData);

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
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        // return productService.save(product);

        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        productService.deleteOne(id);;
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId) {
        productService.addSupplier(supplier, productId);
    }

    @PostMapping("/search/name")
    public Product findByName(@RequestBody SearchData searchData) {
        return productService.findByProductName(searchData.getSearchKey());
    }
    
    @PostMapping("/search/name-like")
    public List<Product> findByNameLike(@RequestBody SearchData searchData) {
        return productService.findByProductNameLike(searchData.getSearchKey());
    }

    @GetMapping("/search/category/{categoryId}")
    public List<Product> findByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    @GetMapping("/search/supplier/{supplierId}")
    public List<Product> findBySupplier(@PathVariable("supplierId") Long supplierId) {
        return productService.findBySupplier(supplierId);
    }
}
