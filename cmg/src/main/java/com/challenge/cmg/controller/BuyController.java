package com.challenge.cmg.controller;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("buy")
public class BuyController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BuyRepository repository;

    @GetMapping
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
    public Buy create(@RequestBody @Valid Buy buy) {
        log.info("cadastrando compra {}", buy);
        return repository.save(buy);
    }

    @GetMapping("{id}")
    public ResponseEntity<Buy> show (@PathVariable Long id) {
        log.info("buscando compra por id {}", id);
        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando compra com id {}");
        verifyExistingBuy(id);

        repository.deleteById(id);
    }

    @PutMapping("{id}")
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
