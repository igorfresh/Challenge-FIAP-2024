package com.challenge.cmg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity

public class Client {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Nome do cliente é obrigatório")
    private String nome;
    @Email
    private String email;
    @Positive
    private int idade;
    //VALIDAR MASCULINO OU FEMININO
    private String genero;
    @Positive
    private int compra;
}
