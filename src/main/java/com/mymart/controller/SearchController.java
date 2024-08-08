package com.mymart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mymart.model.Aboutus;
import com.mymart.model.CarouselImage;
import com.mymart.model.Consumerpolicy;
import com.mymart.model.Customersupport;
import com.mymart.model.DropdownItem;
import com.mymart.model.Mailus;
import com.mymart.model.MovingCards;
import com.mymart.model.NavLink;
import com.mymart.model.Popup;
import com.mymart.model.Product;
import com.mymart.model.Roaddress;
import com.mymart.repository.AboutusRepository;
import com.mymart.repository.ConsumerpolicyRepository;
import com.mymart.repository.CustomersupportRepository;
import com.mymart.repository.DownloadappsRepository;
import com.mymart.repository.KeepintouchRepository;
import com.mymart.repository.MailusRepository;
import com.mymart.repository.MovingCardsRepository;
import com.mymart.repository.PopupRepository;
import com.mymart.repository.RoaddressRepository;
import com.mymart.service.CardItemService;
import com.mymart.service.CarouselImageService;
import com.mymart.service.NavBarService;
import com.mymart.service.ProductService;

@Controller
public class SearchController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private NavBarService navBarService;
    
    @Autowired
   	private CardItemService cardItemService;
   	
       @Autowired
   	private CarouselImageService carouselImageService;
    
    @Autowired
    private AboutusRepository aboutusRepository;

    @Autowired
    private ConsumerpolicyRepository consumerpolicyRepository;

    @Autowired
    private CustomersupportRepository customersupportRepository;
    
    @Autowired
    private DownloadappsRepository downloadappsRepository;

    @Autowired
    private KeepintouchRepository keepintouchRepository;
    
    @Autowired
    private MailusRepository mailusRepository;
    
    @Autowired
    private RoaddressRepository roaddressRepository;
    
    
    @Autowired
  		PopupRepository repo;
    
    
    @Autowired
   	private MovingCardsRepository cardrepo;
  	   

    
    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
    	
    	query=query.trim();
    	
        List<Product> products = productService.searchProducts(query);

        if(query.isEmpty()) {
        	
        	 List<NavLink> allNavLinks = navBarService.getAllNavLinks();
             Map<NavLink, List<DropdownItem>> navbarWithDropdownData = navBarService.getNavbarWithDropdownData();

             
             List<CarouselImage> images = carouselImageService.getAllImages();
     		model.addAttribute("images", images);
             List<Popup> image=repo.findAll();
           		model.addAttribute("image",image);

           		
           		
           		 List<MovingCards> card=cardrepo.findAll();
            		model.addAttribute("card",card);
            
             model.addAttribute("allNavLinks", allNavLinks);
             model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
             
           
             // Get data for the footer from the database
             List<Aboutus> aboutusList = aboutusRepository.findAll();
             List<Consumerpolicy> consumerpolicyList = consumerpolicyRepository.findAll();
             List<Customersupport> customersupportList = customersupportRepository.findAll();
             List<com.mymart.model.Downloadapps> downloadappsList = downloadappsRepository.findAll();
             List<com.mymart.model.Keepintouch> keepintouchList = keepintouchRepository.findAll();
             List<Mailus> mailusList = mailusRepository.findAll();
             List<Roaddress> roaddressList = roaddressRepository.findAll();

             // Add data to the model for footer
             model.addAttribute("aboutusList", aboutusList);
             model.addAttribute("consumerpolicyList", consumerpolicyList);
             model.addAttribute("customersupportList", customersupportList);
             model.addAttribute("downloadappsList", downloadappsList);
             model.addAttribute("keepintouchList", keepintouchList);
             model.addAttribute("mailusList", mailusList);
             model.addAttribute("roaddressList", roaddressList);

             // Add links to each record
             model.addAttribute("googleLink", "https://www.google.com");
             model.addAttribute("cardItems", cardItemService.getAllCardItems());
             // Return the HTML template name
             return "index";
        	
       }
       
        
        if (products.isEmpty()) {
            model.addAttribute("errorMessage", "No products found for the query: " + query);
            return "products/error";
        } else {
            model.addAttribute("products", products);
            return "products/UserProduct"; 
        }
    }
}