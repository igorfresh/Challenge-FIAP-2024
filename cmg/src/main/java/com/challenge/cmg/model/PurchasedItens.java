package com.challenge.cmg.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedItens {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(value = 1, message = "{purchasedItens.quantityItens.min}")
    private int quantityItens;

    @NotNull(message = "{purchasedItens.unityPrice.notnull}")
    @Positive(message = "{purchasedItens.unityPrice.positive}") 
    private BigDecimal unityPrice;
    
    @NotNull(message = "{purchasedItens.idProduct.notblank}")
    @ManyToOne
    private Product product;

    @NotNull(message = "{purchasedItens.idBuy.notblank}")
    @ManyToOne
    private Buy buy;
}
    