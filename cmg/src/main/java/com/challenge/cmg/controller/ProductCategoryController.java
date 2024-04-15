package com.challenge.cmg.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.challenge.cmg.model.ProductCategory;
import com.challenge.cmg.repository.ProductCategoryRepository;

@RestController
@RequestMapping("productCategory")
public class ProductCategoryController {
    
    @RestController
@RequestMapping("productCategory")
public class ProductController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductCategoryRepository repository;

    @GetMapping
    public List<ProductCategory> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ProductCategory create(@RequestBody ProductCategory productCategory) {
        log.info("cadastrando cliente {}", productCategory);
        return repository.save(productCategory);
    } 

    @GetMapping("{id}")
    public ResponseEntity<ProductCategory> show (@PathVariable Long id) {
        log.info("buscando categoria de produto por id {}", id);
        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando categoria de produto cadastrado");
        verifyExistingProductCategory(id);

        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public ProductCategory update (@PathVariable Long id, @RequestBody ProductCategory productCategory) {
        log.info("atualizando produto com id {}", id, productCategory);

        verifyExistingProductCategory(id);

        productCategory.setId(id);
        return repository.save(productCategory);
    } 

    private void verifyExistingProductCategory(Long id) {
        repository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe categoria de produto com o id informado. Consulte lista em /productCategory"));
    }
    
    }
}
