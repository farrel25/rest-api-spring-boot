package practice.restapispringboot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import practice.restapispringboot.dto.SupplierData;
import practice.restapispringboot.models.entities.Supplier;
import practice.restapispringboot.services.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    
    @Autowired
    public SupplierService supplierService;

    @Autowired
    public ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        /**
         * the save method in supplierService need Supplier object as argument,
         * but this create method just accepted DTO class which is SupplierData object,
         * so we need to tranform or map that SupplierData object into Supplier.
         * 
         * there are 2 ways to do this:
         * 1. set all properties in Supplier object with SupplierData properties using setter getter manually
         * 2. use Model Mapper Dependencies to map all matching properties from SupplierData object into Supplier object
         * 
         * for the second option, there are some step to do:
         * 1. add Model Mapper dependencies inside pom.xml
         * 2. create Bean of Model Mapper object, we can write the code inside RestApiSpringBootApplication.java
         * 3. by creating the Bean of Model Mapper object, we can inject it into this controller
         * 4. then we can use the map method from Model Mapper to map all matching properties from SupplierData object into Supplier object automatically
         */
        // Supplier supplier = new Supplier();
        // supplier.setName(supplierData.getName());
        // supplier.setAddress(supplierData.getAddress());
        // supplier.setEmail(supplierData.getEmail());
        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable("id") Long id) {
        return supplierService.findOne(id);
    }

    @GetMapping
    public Iterable<Supplier> findAll() {
        return supplierService.findAll();
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        supplierService.deleteOne(id);
    }

    @PostMapping("/search/email")
    public Supplier findByEmail(@RequestBody SearchData searchData) {
        return supplierService.findByEmail(searchData.getSearchKey());
    }
    
    @PostMapping("/search/name-contains")
    public List<Supplier> findByNameContainsOrderByIdDesc(@RequestBody SearchData searchData) {
        return supplierService.findByNameContainsOrderByIdDesc(searchData.getSearchKey());
    }
    
    @PostMapping("/search/name-starts-with")
    public List<Supplier> findByNameStartsWith(@RequestBody SearchData searchData) {
        return supplierService.findByNameStartsWith(searchData.getSearchKey());
    }

    @PostMapping("/search/name-or-email-contains")
    public List<Supplier> findByNameContainsOrEmailContains(@RequestBody SearchData searchData) {
        return supplierService.findByNameContainsOrEmailContains(searchData.getSearchKey(), searchData.getSearchKey2());
    }
}
