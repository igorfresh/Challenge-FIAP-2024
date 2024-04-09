package com.challenge.cmg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.cmg.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
