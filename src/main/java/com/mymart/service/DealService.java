package com.mymart.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymart.model.Deal;
import com.mymart.model.Product;
import com.mymart.repository.DealRepository;
import com.mymart.repository.ProductsRepository;

@Service
public class DealService {
    private final DealRepository dealRepository;

    @Autowired
    public DealService(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }
    @Autowired
    public ProductsRepository productRepository;

    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    public Optional<Deal> getDealById(int id) {
        return dealRepository.findById(id);
    }

    public Deal createDeal(Deal deal) {
        deal.setStartDate(LocalDate.now()); // Set start date to current date
        return dealRepository.save(deal);
    }
    public void save(Deal deal) {
        dealRepository.save(deal);
    }
   
    public void deleteDeal(int id) {
        dealRepository.deleteById(id);
    }
    public List<Product> getProductsWithDiscountDeals() {
        return productRepository.findProductsWithDealsDiscount();
    }

	 public Deal findById(int id) {
        return dealRepository.findById(id).orElse(null);
    }
}