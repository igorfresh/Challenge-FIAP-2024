package com.challenge.cmg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CmgApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmgApplication.class, args);
	}

}
