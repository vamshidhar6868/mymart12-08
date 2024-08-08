package com.mymart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mymart.model.ContactUs;
import com.mymart.model.Deal;
import com.mymart.model.DropdownItem;
import com.mymart.model.Faq;
import com.mymart.model.NavLink;
import com.mymart.model.Product;
import com.mymart.repository.ContactUsRepository;
import com.mymart.repository.FaqRepository;
import com.mymart.repository.ProductsRepository;
import com.mymart.repository.RatingRepository;
import com.mymart.service.DealService;
import com.mymart.service.NavBarService;
import com.mymart.service.RatingService;

@Controller
public class NavbarController {
	
	 @Autowired
	 DealService dealService;
	 
	 @Autowired
	 ProductsRepository repo;
	 @Autowired
	 UserProductsController userProduct;
	 
	 @Autowired
	 private RatingService ratingService;
	 
	 @Autowired
	 ContactUsRepository contactRepo;
	 @Autowired
	    private FaqRepository faqRepository;

	 @Autowired
     private RatingRepository ratingRepository;  
	 @Autowired
	    private NavBarService service; 
	 @GetMapping("/Admin/Deals")
	    public String adminPage(Model model) {
		 Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
		 model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
		    model.addAttribute("products", dealService.getProductsWithDiscountDeals());

	        return "products/AdminDeals";
	    }
	 @GetMapping("/Deals")
	    public String adminPagea(Model model) {
	        List<Product> products = dealService.getProductsWithDiscountDeals();
	        List<Deal> deals = dealService.getAllDeals();

	        Map<Integer, Double> averageRatings = new HashMap<>();
	        Map<Integer, Integer> ratingCounts = new HashMap<>();
	        Map<Integer, String> ratingColors = new HashMap<>();

	        for (Product product : products) {
	            double averageRating = ratingService.calculateAverageRating(product);
	            Map<String, Integer> counts = userProduct.getProductRatingsAndReviewsCount(product.getId());

	            averageRatings.put(product.getId(), averageRating);
	            ratingCounts.put(product.getId(), counts.get("ratingCount"));
	            ratingColors.put(product.getId(), ratingService.determineRatingColor(averageRating, false)); // Pass false for average rating

	        }

	        model.addAttribute("products", products);
	        model.addAttribute("deals", deals);
	        model.addAttribute("averageRatings", averageRatings);
	        model.addAttribute("ratingCounts", ratingCounts);
	        model.addAttribute("ratingColors", ratingColors);

	        Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
	        model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);

	        return "products/UserProduct";
	    }

	
	 @GetMapping("/Contact")
	 public String userContact(Model model) {
		 Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
		 model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
	        ContactUs latestContact = contactRepo.findFirstByOrderByIdDesc();
	        model.addAttribute("contactUs", latestContact);
	        return "Contact";
	    }
	 @GetMapping("/Admin/Contact")
	    public String adminContact(Model model) {
		 Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
		 model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
	        model.addAttribute("contactUs", new ContactUs());
	        return "admincontact";
	    }
	 @PostMapping("/admincontact") // Add this mapping for handling POST requests
	    public String adminContactSubmit(@ModelAttribute ContactUs contactUs) {
		
	        contactRepo.save(contactUs);
	        return "redirect:/Admin/Contact";
	    }
	 @GetMapping("/Admin/Faqs")
	    public String faqPage(Model model) {
		 Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
		 model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
	        model.addAttribute("faqs", faqRepository.findAll());
	        return "faq";
	    }
	    
	    @GetMapping("/Faqs")
	    public String faqUserPage(Model model) {
	    	Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
	    	model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
	        model.addAttribute("faqs", faqRepository.findAll());
	        return "faqs";
	    }

	    @GetMapping("/faq/new")
	    public String newFaq(Model model) {
	        model.addAttribute("faq", new Faq());
	        return "new_faq";
	    }

	    @PostMapping("/faq/save")
	    public String saveFaq(@ModelAttribute Faq faq) {
	        faqRepository.save(faq);
	        return "redirect:/Admin/Faqs";
	    }

	    @GetMapping("/faq/edit/{id}")
	    public String editFaq(@PathVariable Long id, Model model) {
	        Optional<Faq> faq = faqRepository.findById(id);
	        faq.ifPresent(value -> model.addAttribute("faq", value));
	        return "edit_faq";
	    }

	    @PostMapping("/faq/update/{id}")
	    public String updateFaq(@PathVariable Long id, @ModelAttribute Faq updatedFaq) {
	        Optional<Faq> optionalFaq = faqRepository.findById(id);
	        optionalFaq.ifPresent(faq -> {
	            faq.setQuestion(updatedFaq.getQuestion());
	            faq.setAnswer(updatedFaq.getAnswer());
	            faqRepository.save(faq);
	        });
	        return "redirect:/Admin/Faqs";
	    }

	    @PostMapping("/faq/delete/{id}")
	    public String deleteFaq(@PathVariable Long id) {
	        faqRepository.deleteById(id);
	        return "redirect:/Admin/Faqs";
	    }
}
