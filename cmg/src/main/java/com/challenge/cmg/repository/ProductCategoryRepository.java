package com.challenge.cmg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.cmg.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> { 
        
    Page<ProductCategory> findByName(String name, Pageable pageable);

}
