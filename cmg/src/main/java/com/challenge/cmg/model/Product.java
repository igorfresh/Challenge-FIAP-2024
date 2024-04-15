package com.challenge.cmg.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity

public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "{product.name.notblank}")
    @Size(min=3, message = "{product.name.size}")
    private String name;
    @Positive(message = "{product.price.positive}")
    private BigDecimal price;
    
    
}
