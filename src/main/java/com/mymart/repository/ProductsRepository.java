package com.mymart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mymart.model.Category;
import com.mymart.model.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer> {

	
	Optional<Product> findById(Long id);

	List<Product> findByCategory(Category category);

	List<Product> findByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrCategoryNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
			String query, String query2, String query3, String query4);

	@Query("SELECT p FROM Product p JOIN p.deal d WHERE d.discount > 0")
    List<Product> findProductsWithDealsDiscount();

	@Query("SELECT p FROM Product p " +
	           "LEFT JOIN p.category c " +
	           "WHERE LOWER(p.name) LIKE %:query% " +
	           "OR LOWER(p.brand) LIKE %:query% " +
	           "OR LOWER(c.name) LIKE %:query% " +
	           "OR LOWER(p.description) LIKE %:query% ")
	    List<Product> findBySearchQuery(@Param("query") String query);

	    List<Product> findByCategoryNameContainingIgnoreCase(String categoryName);
	}
	
