package com.challenge.cmg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
	info = @Info(
		title = "API do PersonalBuy",
		description = "Api de recomendação de produtos baseado na compra feita pelo usuário",
		contact = @Contact(name = "Igor Silva", email = "rm99495@fiap.com.br"),
		version = "1.0.0"
	)
)
public class CmgApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmgApplication.class, args);
	}

}
