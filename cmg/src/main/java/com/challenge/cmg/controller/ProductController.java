package com.challenge.cmg.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.challenge.cmg.model.Product;
import com.challenge.cmg.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
@CacheConfig(cacheNames = "produtos")
@RequestMapping("product")
@Tag(name = "produtos", description = "Produtos disponíveis e que serão recomendados")
public class ProductController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductRepository repository;

    @GetMapping
    @Cacheable
    @Operation(
        summary = "Listar produtos cadastrados",
        description = "Retorna uma página com todos os produtos cadastrados ordenados em ordem alfabética"
    )
    public Page<Product> index(
        @RequestParam(required = false) String name,
        @PageableDefault(size = 3, sort = "name", direction = Direction.ASC) Pageable pageable
    ) {
        if(name != null) {
            return repository.findByName(name, pageable);
        }
        return repository.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar produto",
        description = "Cadastra um novo produto com os dados do corpo da requisição."
    )
    public Product create(@RequestBody @Valid Product product) {
        log.info("cadastrando cliente {}", product);
        return repository.save(product);
    } 

    @GetMapping("{id}")
    @Operation(
        summary = "Listar produto por ID",
        description = "Retorna um determinado produto correspondente com o ID selecionado."
    )
    public ResponseEntity<Product> show (@PathVariable Long id) {
        log.info("buscando produto por id {}", id);
        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar produto",
        description = "Deleta um determinado produto correspondente com o ID selecionado."
    )
    public void destroy(@PathVariable Long id) {
        log.info("Apagando produto cadastrado");
        verifyExistingProduct(id);

        repository.deleteById(id);
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar produto",
        description = "Atualiza um determinado produto correspondente com o ID selecionado."
    )
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
