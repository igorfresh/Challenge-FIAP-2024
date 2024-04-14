package com.challenge.cmg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.cmg.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> { 
        
}
