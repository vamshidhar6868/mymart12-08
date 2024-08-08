package com.mymart.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mymart.model.Category;
import com.mymart.model.Deal;
import com.mymart.model.Product;
import com.mymart.model.ProductDto;
import com.mymart.model.Rating;
import com.mymart.model.User;
import com.mymart.repository.ProductsRepository;
import com.mymart.repository.RatingRepository;
import com.mymart.service.CategoryService;
import com.mymart.service.DealService;
import com.mymart.service.FilterService;
import com.mymart.service.ProductService;
import com.mymart.service.RatingService;
import com.mymart.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/User")
public class UserProductsController {
    
    private final ProductsRepository repo;
    private final DealService dealService;
    private final FilterService filterService;
    private final UserService userService;
    private final RatingRepository ratingRepository;
    private final ProductService productService;
    private final CategoryService categoryService;
    
    @Autowired
    private final RatingService ratingService;

    

    @Autowired
    public UserProductsController(ProductsRepository repo, DealService dealService, FilterService filterService, 
                                  UserService userService, RatingRepository ratingRepository, 
                                  ProductService productService, CategoryService categoryService, RatingService ratingService) {
        this.repo = repo;
        this.dealService = dealService;
        this.filterService = filterService;
        this.userService = userService;
        this.ratingRepository = ratingRepository;
        this.productService = productService;
        this.categoryService = categoryService;
        this.ratingService= ratingService;
    }
   
    
    @GetMapping("{categoryName}")
    public String displayProductsByCategory(@PathVariable String categoryName, Model model) {
        Category category = categoryService.getCategoryByName(categoryName);

        if (category == null) {
            return "error";
        }

        List<Product> products = productService.getProductsByCategory(category);
        List<Deal> deals = dealService.getAllDeals();
            
        Map<Integer, Double> averageRatings = new HashMap<>();
        Map<Integer, Integer> ratingCounts = new HashMap<>();
        Map<Integer, String> ratingColors = new HashMap<>();
            
        for (Product product : products) {
            double averageRating = ratingService.calculateAverageRating(product);
            Map<String, Integer> counts = getProductRatingsAndReviewsCount(product.getId());
                
            averageRatings.put(product.getId(), averageRating);
            ratingCounts.put(product.getId(), counts.get("ratingCount"));
            ratingColors.put(product.getId(), ratingService.determineRatingColor(averageRating, false)); // Pass false for average rating
        }

        model.addAttribute("deals", deals);
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        model.addAttribute("averageRatings", averageRatings);
        model.addAttribute("ratingCounts", ratingCounts);
        model.addAttribute("ratingColors", ratingColors);
            
        return "products/UserProduct"; 
    }


    @GetMapping("/All Products")
    public String showProductList(Model model) {       
        List<Product> products = repo.findAll();
        List<Deal> deals = dealService.getAllDeals();
            
        Map<Integer, Double> averageRatings = new HashMap<>();
        Map<Integer, Integer> ratingCounts = new HashMap<>();
        Map<Integer, String> ratingColors = new HashMap<>();
            
        for (Product product : products) {
            double averageRating = ratingService.calculateAverageRating(product);
            Map<String, Integer> counts = getProductRatingsAndReviewsCount(product.getId());
                
            averageRatings.put(product.getId(), averageRating);
            ratingCounts.put(product.getId(), counts.get("ratingCount"));
            ratingColors.put(product.getId(), ratingService.determineRatingColor(averageRating, false)); // Pass false for average rating
        }

        model.addAttribute("products", products);
        model.addAttribute("averageRatings", averageRatings);
        model.addAttribute("ratingCounts", ratingCounts);
        model.addAttribute("deals", deals);
        model.addAttribute("ratingColors", ratingColors);
            
        return "products/UserProduct";
    }


    @GetMapping("/viewproduct")
    public String showEditPage1(Model model, @RequestParam int id,
                                @RequestParam(defaultValue = "latestReviews") String sortField,
                                Principal principal) {
        try {
            model.addAttribute("categories", categoryService.getAllCategories());

            Product product = repo.findById(id).orElse(null);
            if (product == null) {
                return "redirect:/Products";
            }

            model.addAttribute("product", product);

            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setBrand(product.getBrand());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());
            model.addAttribute("productDto", productDto);

            List<Rating> reviews = ratingRepository.findAllByProduct(product, ratingService.getSortSpecification(sortField));

            model.addAttribute("reviews", reviews);
            model.addAttribute("sortField", sortField);
            model.addAttribute("ratingService", ratingService);

            double averageRating = ratingService.calculateAverageRating(product);
            model.addAttribute("averageRating", averageRating);

            Map<String, Integer> counts = getProductRatingsAndReviewsCount(id);
            model.addAttribute("ratingCount", counts.get("ratingCount"));
            model.addAttribute("reviewCount", counts.get("reviewCount"));

            String ratingColor = ratingService.determineRatingColor(averageRating, false); // false for average rating
            model.addAttribute("ratingColor", ratingColor);

            if (principal != null) {
                String username = principal.getName();
                User currentUser = userService.findByEmail(username);
                Rating userRating = ratingRepository.findByUserAndProduct(currentUser, product);
                model.addAttribute("userRating", userRating != null ? userRating.getRating() : 0);
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/Products";
        }
        return "products/viewproduct";
    }



    @PostMapping("/rateProduct")
    public String rateProduct(@RequestParam("productId") int productId,
                              @RequestParam("rating") double rating,
                              @RequestParam("review") String review,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes) {

        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            redirectAttributes.addFlashAttribute("error", "Please log in to submit.");
            return "redirect:/User/viewproduct?id=" + productId;
        }

        User currentUser = userService.getCurrentUser();
        Product product = productService.getProductById(productId);

        if (product == null || currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid product or user.");
            return "redirect:/User/viewproduct?id=" + productId;
        }

        Rating existingRating = ratingRepository.findByUserAndProduct(currentUser, product);
        LocalDateTime dateTime = LocalDateTime.now();

        

        if (existingRating != null) {
            existingRating.setRating(rating);
            existingRating.setReview(review);
            existingRating.setDateTime(dateTime);
            ratingRepository.save(existingRating);
        } else {
            Rating newRating = new Rating();
            newRating.setUser(currentUser);
            newRating.setProduct(product);
            newRating.setRating(rating);
            newRating.setReview(review);
            newRating.setDateTime(dateTime);
            ratingRepository.save(newRating);
        }

        ratingService.rateProduct(currentUser, product, rating);
        redirectAttributes.addFlashAttribute("success", "Rating and review submitted successfully.");
        return "redirect:/User/viewproduct?id=" + productId;
    }

 


  public Map<String, Integer> getProductRatingsAndReviewsCount(int productId) {
      Product product = productService.getProductById(productId);
      if (product == null) {
          return Map.of("ratingCount", 0, "reviewCount", 0);
      }
      List<Rating> ratings = product.getRatings();
      long ratingCount = ratings.size();
      long reviewCount = ratings.stream()
          .filter(rating -> rating.getReview() != null && !rating.getReview().isEmpty())
          .map(Rating::getUser)
          .distinct()
          .count();

      return Map.of("ratingCount", (int) ratingCount, "reviewCount", (int) reviewCount);
  }
  
}
