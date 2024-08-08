package com.mymart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymart.model.Product;
import com.mymart.model.User;
import com.mymart.model.Wishlist;
import com.mymart.repository.WishlistRepository;

@Service
public class WishListService {
    @Autowired
    private WishlistRepository wishlistRepository;

   


    public void addToWishlist(User user, Product product) {
        Wishlist existingItem = wishlistRepository.findByUserAndProduct(user, product);

        if (existingItem != null) {
            // Product already exists in wishlist, remove it
            wishlistRepository.delete(existingItem);
        } else {
            // Product does not exist in wishlist, add it
            Wishlist wishlistItem = new Wishlist();
            wishlistItem.setUser(user);
            wishlistItem.setProduct(product);
            wishlistRepository.save(wishlistItem);
        }
    }

    
    public void removeFromWishlist(User user, Product product) {
        Wishlist wishlistItem = wishlistRepository.findByUserAndProduct(user, product);
        if (wishlistItem != null) {
            wishlistRepository.delete(wishlistItem);
        }
    }

   
    public List<Wishlist> getWishlistByUser(User user) {
        return wishlistRepository.findByUser(user);
    }


    public boolean isProductInWishlist(User user, Product product) {
        // Check if the product is in the user's wishlist by querying the database
        return wishlistRepository.existsByUserAndProduct(user, product);
    }
    
   
}