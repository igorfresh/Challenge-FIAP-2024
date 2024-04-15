package com.challenge.cmg.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity

public class Buy {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String idCLient;
    private Date datePurchase;
    private String purchaseStatus;
    @Positive
    private BigDecimal totalPurchaseValue;
    
}
