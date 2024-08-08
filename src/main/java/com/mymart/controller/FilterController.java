package com.mymart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mymart.ProductFilter;
import com.mymart.model.Filter;
import com.mymart.service.FilterService;


@Controller
public class FilterController {

   
    @Autowired
    private FilterService filterService;


    @GetMapping("/products/filtered")
    public String getFilteredProducts(Model model,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String brand,
                                      @RequestParam(required = false) String category,
                                      @RequestParam(required = false) Double price) {
        ProductFilter filter = new ProductFilter(name, brand);
        filter.setCategory(category);
        filter.setPrice(price);
        List<Filter> filteredProducts = filterService.findProductsByFilter(filter);
        model.addAttribute("filteredProducts", filteredProducts);
        return "products/UserProduct";
    }
}
