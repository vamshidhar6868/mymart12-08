package com.mymart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mymart.model.Deal;
import com.mymart.model.Product;
import com.mymart.repository.CategoryRepository;
import com.mymart.repository.DealRepository;
import com.mymart.repository.ProductsRepository;
import com.mymart.service.CategoryService;
import com.mymart.service.DealService;
import com.mymart.service.ProductService;


@Controller
//@RequestMapping("/deals")
public class DealController {
	
	 private final DealService dealService;
	   

	    public DealController(DealService dealService, ProductService productService, CategoryService categoryService) {
	        this.dealService = dealService;
	        
	    }
       @Autowired
       private ProductsRepository productRepository;	
       @Autowired
       private CategoryRepository categoryRepository;	
       
       @Autowired
       private DealRepository dealRepository;	
	    
       @Autowired
       private ProductService productService;	
	    



    @GetMapping("/deals/addProductDeal")
    public String showAddProductDealForm(Model model) {
        model.addAttribute("deal", new Deal());
        model.addAttribute("products", productRepository.findAll());
        return "products/add-deal";
    }
    @GetMapping("/Admin/deals/addProductDeal")
    public String showAddProductDealkkForm(Model model) {
        model.addAttribute("deal", new Deal());
        model.addAttribute("products", productRepository.findAll());
        return "products/add-deal";
    }
   


    @PostMapping("/deals/addProductDeal")
    public String addProductDeal(@ModelAttribute Deal deal) {
        Product product = productRepository.findById(deal.getProduct().getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.setDeal(deal);

        product.updateDiscountedPrice();

        dealRepository.save(deal);

        return "redirect:/Admin/Deals";
    }
    @GetMapping("/deals/editProductDeal/{dealId}")
    public String showEditProductDealForm(@PathVariable int dealId, Model model) {
        Deal deal = dealRepository.findById(dealId)
                .orElseThrow(() -> new IllegalArgumentException("Deal not found"));

        model.addAttribute("deal", deal);
        model.addAttribute("products", productRepository.findAll());
        return "products/edit-deal";
    }


//    @PostMapping("/deals/editProductDeal/{dealId}")
//    public String editProductDeal(@PathVariable int dealId, @ModelAttribute Deal deal) {
//        Deal existingDeal = dealRepository.findById(dealId)
//                .orElseThrow(() -> new IllegalArgumentException("Deal not found"));
//
//        existingDeal.setName(deal.getName());
//        existingDeal.setDiscount(deal.getDiscount());
//        existingDeal.setStartDate(deal.getStartDate());
//        existingDeal.setEndDate(deal.getEndDate());
//        existingDeal.setProduct(deal.getProduct());
//
//        Product product = existingDeal.getProduct();
//        product.updateDiscountedPrice();
//
//        dealRepository.save(existingDeal);
//
//        return "redirect:/Admin/Deals";
//    }

    @PostMapping("/deals/editProductDeal/{dealId}")
    @Transactional
    public String editProductDeal(@PathVariable int dealId, @ModelAttribute Deal deal) {
        Deal existingDeal = dealRepository.findById(dealId)
                .orElseThrow(() -> new IllegalArgumentException("Deal not found"));

        existingDeal.setName(deal.getName());
        existingDeal.setDiscount(deal.getDiscount());
        existingDeal.setStartDate(deal.getStartDate());
        existingDeal.setEndDate(deal.getEndDate());
        existingDeal.setProduct(deal.getProduct());

        existingDeal.getProduct().updateDiscountedPrice();
        dealRepository.save(existingDeal);

        return "redirect:/Admin/Deals";
    }
   
    
    

    
    @PostMapping("/deals/delete/{id}")
    public String deleteDeal(@PathVariable("id") int id) {
        dealService.deleteDeal(id);
        return "redirect:/Admin/Deals";
    }
    @GetMapping("/deals/discount")
    public String showProductsWithDiscount(Model model) {
        model.addAttribute("products", dealService.getProductsWithDiscountDeals());
        return "products/UserProduct"; 
    }
}