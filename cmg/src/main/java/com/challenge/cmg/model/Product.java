package com.challenge.cmg.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity

public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Nome do produto é obrigatório")
    private String nome;
    private String descricao;
    private String categoria;
    private BigDecimal preço;
    private String marca;
    
    
}
