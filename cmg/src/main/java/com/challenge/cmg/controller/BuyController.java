package com.challenge.cmg.controller;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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

import com.challenge.cmg.model.Buy;
import com.challenge.cmg.repository.BuyRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CacheConfig(cacheNames = "compras")
@RequestMapping("buy")
@Tag(name = "compras", description = "Compras realizadas")
public class BuyController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BuyRepository repository;

    @GetMapping
    @Cacheable
    @Operation(
        summary = "Listar Compras",
        description = "Retorna uma página com todas as compras cadastradas ordenada pelo data da compra"
    )
    public Page<Buy> index(
        @RequestParam(required = false) LocalDate datePurchase,
        @PageableDefault(size = 3, sort = "datePurchase", direction = Direction.ASC) Pageable pageable
    ){
        if (datePurchase != null){
            return repository.findByDatePurchase(datePurchase, pageable);
        }

        return repository.findAll(pageable);

    }

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar compra",
        description = "Cadastra uma nova compra com os dados do corpo da requisição."
    )
    public Buy create(@RequestBody @Valid Buy buy) {
        log.info("cadastrando compra {}", buy);
        return repository.save(buy);
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Listar compra por ID",
        description = "Retorna uma determinada compra correspondente com o ID selecionado."
    )
    public ResponseEntity<Buy> show (@PathVariable Long id) {
        log.info("buscando compra por id {}", id);
        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar compra",
        description = "Deleta uma determinada compra correspondente com o ID selecionado."
    )
    public void destroy(@PathVariable Long id) {
        log.info("Apagando compra com id {}");
        verifyExistingBuy(id);

        repository.deleteById(id);
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar compra",
        description = "Atualiza uma determinada compra correspondente com o ID selecionado."
    )
    public Buy update (@PathVariable Long id, @RequestBody Buy buy) {
        log.info("atualizando compra com id {}", id, buy);

        verifyExistingBuy(id);

        buy.setId(id);
        return repository.save(buy);
    }

    private void verifyExistingBuy(Long id) {
        repository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe compra com o id informado. Consulte lista em /buy"));
    }
}
