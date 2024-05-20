package com.challenge.cmg.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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

    @NotNull(message = "{buy.idclient.notblank}")
    @ManyToOne
    private Client client;

    public Buy(Long id) {
        this.id = id;
    }

}
