package com.challenge.cmg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

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

import com.challenge.cmg.model.Client;
import com.challenge.cmg.repository.ClientRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("client")
public class ClientController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ClientRepository repository;

    @GetMapping
    public List<Client> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Client create(@RequestBody @Valid Client client) {
        log.info("cadastrando cliente {}", client);
        return repository.save(client);
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> show(@PathVariable Long id) {
        log.info("buscando cliente por id {}", id);
        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando cadastro de cliente");
        verifyExistingClient(id);

        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public Client update (@PathVariable Long id, @RequestBody Client client) {
        log.info("atualizando cliente com id {} para {}", id, client);

        verifyExistingClient(id);

        client.setId(id);
        return repository.save(client);
    }

    private void verifyExistingClient(Long id) {
        repository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Não existe cliente com o id informado. Consulte lista em /client"));
    }

}