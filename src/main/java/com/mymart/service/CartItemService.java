package com.mymart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymart.model.Address;
import com.mymart.model.CartItem;
import com.mymart.model.Product;
import com.mymart.model.User;
import com.mymart.repository.CartItemRepository;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAllCartItemsByUser(User user) {
        return cartItemRepository.findByUser(user);
    }


    
    public void addToCart(User user, Product product, int quantity) {
        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
        double totalPrice;

        if (product.getDeal() != null) { // Check if the product has a deal
            double discountPercentage = product.getDeal().getDiscount();
            double discountedPrice = product.getPrice() * (1 - discountPercentage / 100);
            totalPrice = discountedPrice * quantity;
            product.setDiscountedPrice(discountedPrice); // Set discounted price to product
        } else {
            totalPrice = product.getPrice() * quantity; // Use original price if no deal
        }

        if (existingCartItem != null) {
            if (product.getDeal() != null) { // Check if the product has a discount
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                existingCartItem.setTotalPrice(existingCartItem.getTotalPrice() + totalPrice); // Update total price with discounted price
            } else {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                existingCartItem.setTotalPrice(existingCartItem.getTotalPrice() + (product.getPrice() * quantity)); // Update total price with original price
            }
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setTotalPrice(totalPrice);
            cartItemRepository.save(cartItem);
        }
    }


    public void removeFromCart(int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
    


    public void updateQuantity(int cartItemId, int quantityChange) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
        if (cartItem != null) {
            int newQuantity = cartItem.getQuantity() + quantityChange;
            if (newQuantity >= 1) {
                cartItem.setQuantity(newQuantity);

                Product product = cartItem.getProduct();
                if (product.getDeal() != null) { // Check if the product has a deal
                    double discountPercentage = product.getDeal().getDiscount();
                    double discountedPrice = product.getPrice() * (1 - discountPercentage / 100);
                    cartItem.setTotalPrice(discountedPrice * newQuantity);
                    product.setDiscountedPrice(discountedPrice); // Update discounted price in product
                } else {
                    cartItem.setTotalPrice(product.getPrice() * newQuantity); // Use original price if no deal
                }

                cartItemRepository.save(cartItem);
            }
        }
    }

	public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }


	
	public double calculateSubtotal(List<CartItem> cartItems) {
	    double subtotal = 0.0;
	    for (CartItem item : cartItems) {
	        double price = item.getProduct().getDeal() != null ?
	                item.getProduct().getDiscountedPrice() : item.getProduct().getPrice();
	        subtotal += price * item.getQuantity();
	    }
	    return subtotal;
	}

	    public double calculateShipping(double subtotal) {
	        
	        return 15; 
	    }

		public void clearCart(User user) {
    List<CartItem> cartItems = cartItemRepository.findByUser(user);
    cartItemRepository.deleteAll(cartItems);
}



}