package com.mymart.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymart.ProductFilter;
import com.mymart.model.Filter;
import com.mymart.repository.FilterRepository;

@Service
public class FilterService {

	 @Autowired
	    private FilterRepository productRepository;

	   
	    public List<Filter> findAllProducts() {
	        return productRepository.findAll();
	    }

	   
	    public List<Filter> findProductsByFilter(ProductFilter filter) {
	        List<Filter> allProducts = productRepository.findAll();
	        List<Filter> filteredProducts = allProducts.stream()
	                .filter(product -> (filter.getName() == null || product.getName().toLowerCase().contains(filter.getName().toLowerCase())) &&
	                        (filter.getBrand() == null || product.getBrand().toLowerCase().contains(filter.getBrand().toLowerCase())) &&
	                        (filter.getCategory() == null || product.getCategroy().toLowerCase().contains(filter.getCategory().toLowerCase())) &&
	                        (filter.getPrice() == null || product.getPrice() <= filter.getPrice()))
	                .collect(Collectors.toList());
	        return filteredProducts;
	    }

	   
	    public List<Filter> findAll() {
	        
	        return null;
	    }
}

