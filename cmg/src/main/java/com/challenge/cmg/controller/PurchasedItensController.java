package com.challenge.cmg.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.math.BigDecimal;

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

import com.challenge.cmg.model.PurchasedItens;
import com.challenge.cmg.repository.PurchasedItensRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CacheConfig(cacheNames = "itens comprados")
@RequestMapping("purchasedItens")
@Tag(name = "Itens comprados", description = "Itens comprados em cada compra")
public class PurchasedItensController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    PurchasedItensRepository repository;

    @GetMapping
    @Cacheable
    @Operation(
        summary = "Listar itens da compra",
        description = "Retorna uma página com todas os itens de uma determinada compra ordenada pelo preço."
    )
    public Page<PurchasedItens> index(
        @RequestParam(required = false) BigDecimal unityPrice,
        @PageableDefault(size = 3, sort = "unityPrice", direction = Direction.ASC) Pageable pageable
    ) {
        if(unityPrice != null) {
            return repository.findByUnityPrice(unityPrice, pageable);
        }
        return repository.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar itens comprados",
        description = "Cadastra novos itens comprados com os dados do corpo da requisição."
    )
    public PurchasedItens create(@RequestBody @Valid PurchasedItens purchasedItens) {
        log.info("cadastrando itens comprados {}", purchasedItens);
        return repository.save(purchasedItens);
    } 

    @GetMapping("{id}")
    @Operation(
        summary = "Listar itens comprados por ID",
        description = "Retorna uma determinada compra de itens correspondente com o ID selecionado."
    )
    public ResponseEntity<PurchasedItens> show (@PathVariable Long id) {
        log.info("buscando itens comprados por id {}", id);
        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar itens comprados",
        description = "Deleta determinados itens comprados correspondente com o ID selecionado."
    )
    public void destroy(@PathVariable Long id) {
        log.info("Apagando itens comprados");
        verifyExistingProduct(id);

        repository.deleteById(id);
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar itens comprados",
        description = "Atualiza determinados itens comprados correspondente com o ID selecionado."
    )
    public PurchasedItens update (@PathVariable Long id, @RequestBody PurchasedItens purchasedItens) {
        log.info("atualizando itens comprados com id {}", id, purchasedItens);

        verifyExistingProduct(id);

        purchasedItens.setId(id);
        return repository.save(purchasedItens);
    } 

    private void verifyExistingProduct(Long id) {
        repository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe itens comprados com o id informado. Consulte lista em /purchasedItens"));
    }

}
