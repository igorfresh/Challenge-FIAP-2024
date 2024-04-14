package com.challenge.cmg.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class PurchasedItens {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Product idProduct;
    private Buy idBuy;
    private int quantityItens;
    private BigDecimal unityPrice;
}
    