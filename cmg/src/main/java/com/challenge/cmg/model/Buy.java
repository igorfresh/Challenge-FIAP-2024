package com.challenge.cmg.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity

public class Buy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{buy.datepurchase.notblank}")
    @PastOrPresent(message = "{buy.datepurchase.pastorpresent}")
    private LocalDate datePurchase;

    private String purchaseStatus;

    @Positive(message = "{buy.totalPurchaseValue.positive}")
    private BigDecimal totalPurchaseValue;

    @NotBlank(message = "{buy.idclient.notblank}")
    @ManyToOne
    private Client Client;

    

}
