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

import com.challenge.cmg.model.Product;
import com.challenge.cmg.repository.ProductRepository;



@RestController
@RequestMapping("product")
public class ProductController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductRepository repository;

    @GetMapping
    public List<Product> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Product create(@RequestBody Product product) {
        log.info("cadastrando cliente {}", product);
        return repository.save(product);
    } 

    @GetMapping("{id}")
    public ResponseEntity<Product> show (@PathVariable Long id) {
        log.info("buscando produto por id {}", id);
        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando produto cadastrado");
        verifyExistingProduct(id);

        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public Product update (@PathVariable Long id, @RequestBody Product product) {
        log.info("atualizando produto com id {}", id, product);

        verifyExistingProduct(id);

        product.setId(id);
        return repository.save(product);
    } 

    private void verifyExistingProduct(Long id) {
        repository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe produto com o id informado. Consulte lista em /product"));
    }
}
