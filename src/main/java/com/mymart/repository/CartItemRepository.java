package com.mymart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymart.model.CartItem;
import com.mymart.model.Product;
import com.mymart.model.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUser(User user);
    
    CartItem findByUserAndProduct(User user, Product product);

   


	
}