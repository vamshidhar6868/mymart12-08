package com.mymart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymart.model.Category;
import com.mymart.model.Product;
import com.mymart.model.Rating;
import com.mymart.model.User;
import com.mymart.repository.ProductsRepository;
import com.mymart.repository.RatingRepository;

@Service
public class ProductService {


	 private final ProductsRepository productRepository;
	    private final RatingRepository ratingRepository;

	    @Autowired
	    public ProductService(ProductsRepository productsRepository, RatingRepository ratingRepository) {
	        this.productRepository = productsRepository;
	        this.ratingRepository = ratingRepository;
	    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }



    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }
    
    public List<Product> searchProductss(String query) {
        return productRepository.findByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrCategoryNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query, query, query);
    }
    
   
    
    public List<Product> searchProducts(String query) {
        return productRepository.findBySearchQuery(query);
    }



    public void save(Product product) {
        productRepository.save(product);
    }



	 public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
	 
	 public void rateProduct(User user, Product product, double ratingValue) {
	        Rating existingRating = ratingRepository.findByUserAndProduct(user, product);
	        if (existingRating != null) {
	            existingRating.setRating(ratingValue);
	            ratingRepository.save(existingRating);
	        } else {
	            Rating newRating = new Rating();
	            newRating.setUser(user);
	            newRating.setProduct(product);
	            newRating.setRating(ratingValue);
	            ratingRepository.save(newRating);
	        }
	    }
	 
	 public void removeRating(User user, Product product) {
		    Rating existingRating = ratingRepository.findByUserAndProduct(user, product);
		    if (existingRating != null) {
		        ratingRepository.delete(existingRating);
		    }
		}   
}



