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

import com.challenge.cmg.model.ProductCategory;
import com.challenge.cmg.repository.ProductCategoryRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CacheConfig(cacheNames = "categoria de produtos")
@RequestMapping("productCategory")
@Tag(name = "categorias de produto", description = "Categorias de produto disponíveis")
public class ProductCategoryController {
    
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductCategoryRepository repository;

    @GetMapping
    @Cacheable
    @Operation(
        summary = "Listar categorias de produto",
        description = "Retorna uma página com todas categorias de produto cadastradas ordenada por ordem alfabética"
    )
    public Page<ProductCategory> index(
        @RequestParam(required = false) String name,
        @PageableDefault(size = 2, sort = "name", direction = Direction.ASC) Pageable pageable

        ) {
            if (name != null) {
                return repository.findByName(name, pageable);
            }
            return repository.findAll(pageable);
        }

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar categoria de produto",
        description = "Cadastra uma nova categoria de produto com os dados do corpo da requisição."
    )
    public ProductCategory create(@RequestBody @Valid ProductCategory productCategory) {
        log.info("cadastrando cliente {}", productCategory);
        return repository.save(productCategory);
    } 

    @GetMapping("{id}")
    @Operation(
        summary = "Listar categoria de produto por ID",
        description = "Retorna uma determinada categoria de produto correspondente com o ID selecionado."
    )
    public ResponseEntity<ProductCategory> show (@PathVariable Long id) {
        log.info("buscando categoria de produto por id {}", id);
        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar categoria de produto",
        description = "Deleta uma determinada categoria de produto correspondente com o ID selecionado."
    )
    public void destroy(@PathVariable Long id) {
        log.info("Apagando categoria de produto cadastrado");
        verifyExistingProductCategory(id);

        repository.deleteById(id);
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar categoria de produto",
        description = "Atualiza uma determinada categoria de produto correspondente com o ID selecionado."
    )
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