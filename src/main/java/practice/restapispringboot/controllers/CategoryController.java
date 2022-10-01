package practice.restapispringboot.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import practice.restapispringboot.dto.CategoryData;
import practice.restapispringboot.dto.ResponseData;
import practice.restapispringboot.dto.SearchData;
import practice.restapispringboot.models.entities.Category;
import practice.restapispringboot.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    public CategoryService categoryService;

    @Autowired
    public ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryData, Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id) {
        return categoryService.findOne(id);
    }

    @GetMapping
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryData categoryData, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryData, Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        categoryService.deleteOne(id);
    }

    /**
     * paging the query result based on size param (size of each page)
     * and page param (to show which page number)
     */
    @PostMapping("/search/name-contains/{size}/{page}")
    public Iterable<Category> findByNameContains(@RequestBody SearchData searchData, @PathVariable("size") Integer size, @PathVariable("page") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        
        return categoryService.findByNameContains(searchData.getSearchKey(), pageable);
    }

    /**
     * paging the query result based on @param size (size of each page)
     * and @param page (to show which page number). Also sorting based on
     * @param sort with defaut value is asc
     */
    @PostMapping("/search/name-contains/{size}/{page}/{sort}")
    public Iterable<Category> findByNameContains(@RequestBody SearchData searchData, @PathVariable("size") Integer size, @PathVariable("page") Integer page, @PathVariable("sort") String sort) {

        // default sort is ascending
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        if (sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        
        return categoryService.findByNameContains(searchData.getSearchKey(), pageable);
    }

    @PostMapping("/batch")
    public ResponseEntity<ResponseData<Iterable<Category>>> createBatch(@RequestBody Category[] categories) {
        ResponseData<Iterable<Category>> responseData = new ResponseData<>();

        // Arrays.asList(categories)
        /**
         * Arrays.asList(categories)
         * because of saveBatch() method need parameter with Iterable type instead of array of Category,
         * then we need to casting using Arrays.asList()
         */
        responseData.setPayload(categoryService.saveBatch(Arrays.asList(categories)));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }
}
