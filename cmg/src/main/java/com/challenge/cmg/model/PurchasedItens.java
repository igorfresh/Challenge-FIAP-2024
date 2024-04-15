package com.challenge.cmg.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity

public class PurchasedItens {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "{purchasedItens.idProduct.notblank}")
    private String idProduct;
    @NotBlank(message = "{purchasedItens.idBuy.notblank}")
    private String idBuy;
    @Min(value = 1, message = "{purchasedItens.quantityItens.min}")
    private int quantityItens;
    @NotNull(message = "{purchasedItens.unityPrice.notnull}")
    @Positive(message = "{purchasedItens.unityPrice.positive}") 
    private BigDecimal unityPrice;
}
    