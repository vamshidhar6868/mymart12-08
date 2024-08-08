package com.mymart.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mymart.model.Category;
import com.mymart.model.Deal;
import com.mymart.model.DropdownItem;
import com.mymart.model.NavLink;
import com.mymart.model.Product;
import com.mymart.model.ProductDto;
import com.mymart.repository.CategoryRepository;
import com.mymart.repository.ProductsRepository;
import com.mymart.service.CategoryService;
import com.mymart.service.DealService;
import com.mymart.service.NavBarService;
import com.mymart.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Admin")
public class AdminProductsController {
	
	@Autowired
	private ProductsRepository repo;
	
	@Autowired
	private CategoryRepository crepo;
	
	@Autowired
	private NavBarService service;
	
	@Autowired
	private DealService dealservice;
	
	private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public AdminProductsController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("{categoryName}")
    public String displayProductsByCategory(@PathVariable String categoryName, Model model) {
    	
    	Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
    	model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
    	
        Category category = categoryService.getCategoryByName(categoryName);

        if (category == null) {
            return "error";
        }

        List<Product> products = productService.getProductsByCategory(category);

        model.addAttribute("category", category);
        model.addAttribute("products", products);

        return "products/AdminProduct"; 
    }


    

	@GetMapping("/addCategory")
	public String showAddForm(Model model) {
		model.addAttribute("cat", new Category());
		
		
		 List<Category> categories = categoryService.getAllCategories();
	        model.addAttribute("categories", categories);
		return "products/addCategory";
	}
	@PostMapping("/addCategory")
   public String addcategory(@ModelAttribute("cat")Category cat) {
  
   	crepo.save(cat);
   	
   	return "redirect:/Admin/Product";
   }
    
    
    
   
	@GetMapping("/Product")
	public String showProductList(Model model) {
		Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
		model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
		List<Product> products = repo.findAll();
		model.addAttribute("products", products);
		
		 List<Deal> deals = dealservice.getAllDeals();
	        model.addAttribute("deals", deals);
		return "products/AdminProduct";
	}

	
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		ProductDto productDto = new ProductDto();
		model.addAttribute("productDto",productDto);
		
		 List<Category> categories = categoryService.getAllCategories();
	        model.addAttribute("categories", categories);
		return "products/CreateProduct";
	}
	
	@PostMapping("/create")
	public String createProduct(
			@Valid @ModelAttribute ProductDto productDto,
			BindingResult result
			) 
	
	{
		if(productDto.getImageFile().isEmpty()) {
			result.addError(new FieldError("productDto","imageFile","The image file is required"));
		}
		
		if(result.hasErrors()) {
			return "products/CreateProduct";
		}
		
		MultipartFile image = productDto.getImageFile();
			Date createdAt = new Date();
			String storageFileName = createdAt.getTime()+"_"+image.getOriginalFilename();
			
			try {
				String uploadDir = "public/images/";
				Path uploadPath = Paths.get(uploadDir);
				
				if(!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}
			
			try(InputStream inputStream = image.getInputStream()){
				Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
						StandardCopyOption.REPLACE_EXISTING);
			}
		}catch(Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
		}
			
			

			
			Product product = new Product();
			product.setName(productDto.getName());
			product.setBrand(productDto.getBrand());
			
			
			product.setCategory(productDto.getCategory());
			product.setPrice(productDto.getPrice());
			product.setDescription(productDto.getDescription());
			product.setCreatedAt(createdAt);
			product.setImageFileName(storageFileName);
			product.setRatingCount(0); 
			
			product.calculateDiscountedPrice();
			repo.save(product);
		
			return "redirect:/Admin/Product";
	}


	

	
	@GetMapping("/edit")
	public String showEditPage(
			Model model,
			@RequestParam int id
			) {
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
			return "redirect:/Product";
		}
		return "products/EditProduct";
	}
	
	

	@PostMapping("/edit")
	public String updateProduct(
			Model model,
			@RequestParam int id,
			@Valid @ModelAttribute ProductDto productDto,
			BindingResult result, RedirectAttributes redirectAttributes
			) {
		
		try {
			Product product = repo.findById(id).get();
			model.addAttribute("product",product);
			
			if(result.hasErrors()) {
				return "products/EditProduct";
			}
			
			if(!productDto.getImageFile().isEmpty()) {
				//for deleting the old images
				String uploadDir = "public/images/";
				Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());
				try {
					Files.delete(oldImagePath);
				}
				catch(Exception ex) {
					System.out.println("Exception: " + ex.getMessage());
				}
				
				//save the new image
				MultipartFile image = productDto.getImageFile();
				Date createdAt = new Date();
				String storageFileName = createdAt.getTime()+"_"+image.getOriginalFilename();
				
				try(InputStream inputStream = image.getInputStream()){
					Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
							StandardCopyOption.REPLACE_EXISTING);
			}
				product.setImageFileName(storageFileName);
		}
			product.setName(productDto.getName());
			product.setBrand(productDto.getBrand());
			product.setCategory(productDto.getCategory());
			
			
			
			
			 
			
			product.setPrice(productDto.getPrice());
			product.setDescription(productDto.getDescription());

			repo.save(product);
		}
		catch(Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
		
		redirectAttributes.addAttribute("categoryName", productDto.getCategory().getName());
		return "redirect:/Admin/Product";

	}
	
	
	
	

	@GetMapping("/delete")
	public String deleteProduct(
		@RequestParam int id
		) {
		try {
			Product product = repo.findById(id).get();
			
			//for deleting the product image
			Path imagePath = Paths.get("public/images/" + product.getImageFileName());
			
			try {
				Files.delete(imagePath);
			}
			catch(Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
			}
			
			repo.delete(product);
		}
		catch(Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
		return "redirect:/Admin/Product";
	}
	
	
	 @GetMapping("/All Products")
		public String showProductListAdmin(Model model) {	
		 Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
		 model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
			List<Product> products = repo.findAll();
			model.addAttribute("products", products);
			
			List<Deal> deals = dealservice.getAllDeals();
	        model.addAttribute("deals", deals);
			
			return "products/AdminProduct";
		}
	
}


