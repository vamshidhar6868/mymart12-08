package com.mymart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymart.model.Product;
import com.mymart.model.User;
import com.mymart.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

	Wishlist findByUserAndProduct(User user, Product product);

	List<Wishlist> findByUser(User user);

	boolean existsByProductAndUser(Product product, User user);

	int deleteByProductAndUser(Product product, User user);

	boolean existsByUserAndProduct(User user, Product product);

}
