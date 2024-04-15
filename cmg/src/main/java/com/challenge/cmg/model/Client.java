package com.challenge.cmg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "{client.cpf.invalid}")
    private String cpf;
    @NotBlank(message = "{client.email.notblank}") 
    @Email(message = "{client.email.invalid}")
    private String email;
    @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}", message = "{client.phone.invalid}")
    private String phone;
    private String adress;
    private String city;
    private String state;
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "{client.cep.invalid}")
    private String cep;

}
