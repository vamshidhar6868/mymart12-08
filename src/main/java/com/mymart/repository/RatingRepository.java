package com.mymart.repository;

import com.mymart.model.Product;
import com.mymart.model.Rating;
import com.mymart.model.User;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    
	Rating findByUserAndProduct(User user, Product product);
	
	List<Rating> findAllByProduct(Product product, Sort sort);
}