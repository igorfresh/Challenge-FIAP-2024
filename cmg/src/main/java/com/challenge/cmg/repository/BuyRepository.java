package com.challenge.cmg.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.cmg.model.Buy;

public interface BuyRepository extends JpaRepository<Buy, Long>{

    Page<Buy> findByDatePurchase(LocalDate datePurchase, Pageable pageable);
}
