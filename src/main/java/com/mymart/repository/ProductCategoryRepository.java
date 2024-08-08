package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymart.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
