package com.challenge.cmg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @NotBlank (message = "{users.password.notnull}")
    @Size(max = 255, message = "{users.password.size}")
    private String password;

    @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}", message = "{client.phone.invalid}")
    private String phone;

    private String adress;

    private String city;

    private String state;
    
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "{client.cep.invalid}")
    private String cep;


    public Client(Long id) {
        this.id = id;
    }
}
