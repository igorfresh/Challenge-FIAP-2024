package com.challenge.cmg.model;

import org.hibernate.validator.constraints.br.CPF;

import com.challenge.cmg.validation.GenderValidation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity

public class Client {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "{client.name.notblank}")
    @Size(min=3, message = "{client.name.size}")
    private String name;
    @CPF
    private CPF cpf;
    @NotBlank(message = "{client.email.notblank}") 
    @Email
    private String email;
    //@Telefone
    private String phone;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;

}
