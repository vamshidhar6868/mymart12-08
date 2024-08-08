package com.mymart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymart.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	List<Category> findAll();

	Category findByName(String name);


}
