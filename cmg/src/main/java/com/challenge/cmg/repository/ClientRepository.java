package com.challenge.cmg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.cmg.model.Client;

//classe que permite acessar a tabela client

public interface ClientRepository extends JpaRepository<Client, Long>{
    
    


}
