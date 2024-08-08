package com.mymart.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mymart.model.CartItem;
import com.mymart.model.DropdownItem;
import com.mymart.model.NavLink;
import com.mymart.model.Product;
import com.mymart.model.ProductDto;
import com.mymart.model.User;
import com.mymart.repository.ProductsRepository;
import com.mymart.service.CartItemService;
import com.mymart.service.CategoryService;
import com.mymart.service.NavBarService;
import com.mymart.service.ProductService;
import com.mymart.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final CategoryService categoryService;

    
    @Autowired
	private ProductsRepository repo;
    @Autowired
    private NavBarService service; 
    @Autowired
    public CartController(ProductService productService, CartItemService cartItemService, UserService userService,CategoryService categoryService) {
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.categoryService=categoryService;
        
    }


    
    @GetMapping("")
    public String viewCart(Model model, Principal principal) {
    	Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
    	model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByEmail(username);
            List<CartItem> cartItems = cartItemService.getAllCartItemsByUser(user);

           
            
            
            double totalCartPrice = calculateTotalCartPrice(cartItems);

            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalCartPrice", totalCartPrice);
            return "products/cart";
        } else {
            return "redirect:/login";
        }
    }


    private double calculateTotalCartPrice(List<CartItem> cartItems) {
        double totalCartPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            double itemPrice = cartItem.getProduct().getDiscountedPrice() > 0 ?
                    cartItem.getProduct().getDiscountedPrice() : cartItem.getProduct().getPrice();
            totalCartPrice += cartItem.getQuantity() * itemPrice;
        }
        return totalCartPrice;
    }


    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity,
                            Principal principal) {
        if (principal == null) {
           
            return "redirect:/registration";
        }

        String username = principal.getName();
        User user = userService.findByEmail(username);
        Product product = productService.getProductById(productId);
        cartItemService.addToCart(user, product, quantity);
        return "redirect:/cart";
    }

	
    
    @PostMapping("/buyNow")
    public String buyNow(@RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity,
                            Principal principal) {
        if (principal == null) {
           
            return "redirect:/registration";
        }

        String username = principal.getName();
        User user = userService.findByEmail(username);
        Product product = productService.getProductById(productId);
        cartItemService.addToCart(user, product, quantity);
        return "redirect:/checkout";
    }

   
    
	  @PostMapping("/remove") public String
	  removeFromCart(@RequestParam("cartItemId") int cartItemId) {
	  cartItemService.removeFromCart(cartItemId); return "redirect:/cart"; }
	  
	  
	  @PostMapping("/updateQuantity")
	    public String updateQuantity(@RequestParam("cartItemId") int cartItemId,
	                                 @RequestParam("quantityChange") int quantityChange) {
	        cartItemService.updateQuantity(cartItemId, quantityChange);
	        return "redirect:/cart"; 
	    }
	  
	  
	  @GetMapping("/viewproduct")
		public String showEditPage1(
				Model model,
				@RequestParam int id
				) {
		  Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
		  model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
			try {
				model.addAttribute("categories", categoryService.getAllCategories());

				Product product = repo.findById(id).get();
				model.addAttribute("product",product);
				
				ProductDto productDto = new ProductDto();
				productDto.setName(product.getName());
				productDto.setBrand(product.getBrand());
			    productDto.setCategory(product.getCategory());
				
				

				productDto.setPrice(product.getPrice());
				productDto.setDescription(product.getDescription());

				model.addAttribute("productDto",productDto);
				

			}
			catch(Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
				return "redirect:/Products";
			}
			return "products/viewproduct";
		}
	 
	 
	  
	 
}

