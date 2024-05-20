package com.challenge.cmg.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.cmg.model.PurchasedItens;

public interface PurchasedItensRepository extends JpaRepository<PurchasedItens, Long> {

Page<PurchasedItens> findByUnityPrice(BigDecimal unityPrice, Pageable pageable);
}
