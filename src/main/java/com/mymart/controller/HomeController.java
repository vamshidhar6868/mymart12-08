package com.mymart.controller;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mymart.model.Aboutus;
import com.mymart.model.CardItem;
import com.mymart.model.CarouselImage;
import com.mymart.model.Consumerpolicy;
import com.mymart.model.Customersupport;
import com.mymart.model.DropdownItem;
import com.mymart.model.Mailus;
import com.mymart.model.MovingCards;
import com.mymart.model.NavLink;
import com.mymart.model.Popup;
import com.mymart.model.PopupDto;
import com.mymart.model.Roaddress;
import com.mymart.repository.Aboutus1Repository;
import com.mymart.repository.AboutusRepository;
import com.mymart.repository.ConsumerpolicyRepository;
import com.mymart.repository.CustomersupportRepository;
import com.mymart.repository.DownloadappsRepository;
import com.mymart.repository.KeepintouchRepository;
import com.mymart.repository.MailusRepository;
import com.mymart.repository.MovingCardsRepository;
import com.mymart.repository.NavLinkRepository;
import com.mymart.repository.PopupRepository;
import com.mymart.repository.RoaddressRepository;
import com.mymart.repository.UserRepository;
import com.mymart.service.CardItemService;
import com.mymart.service.CarouselImageService;
import com.mymart.service.NavBarService;
import com.mymart.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private NavLinkRepository NavLinkRepository;
    @Autowired
	 private Aboutus1Repository aboutus1Repository;
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
		private MovingCardsRepository cardrepo;
	    @Autowired
		PopupRepository repo;
	   
	
	private final NavBarService service; // Inject your service
	
	
    public HomeController(NavBarService service) {
        this.service = service;
    }
	


	

	@GetMapping("/")
	
public String index(Model model) {
        
        List<NavLink> allNavLinks = service.getAllNavLinks();
        Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();

        
        List<CarouselImage> images = carouselImageService.getAllImages();
		model.addAttribute("images", images);
		
        List<Popup> image=repo.findAll();
      		model.addAttribute("image",image);

      	  List<CardItem> carditem = cardItemService.getAllCardItems();
  		model.addAttribute("carditem", carditem);
  		
  	
  		
      		
      		
      		 List<MovingCards> card=cardrepo.findAll();
       		model.addAttribute("card",card);
        model.addAttribute("allNavLinks", allNavLinks);
        model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
        
      
        // Get data for the footer from the database
        List<Aboutus> aboutusList = aboutus1Repository.findAll();
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
	
	@GetMapping("/Home")
 public String ShowHomwPage(Model model) {
        
        List<NavLink> allNavLinks = service.getAllNavLinks();
        Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();

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
        
		 return "redirect:/";
	}
	
	 @GetMapping("/Admin/Home")
	    public String adminPagess(Model model) {

		 return "redirect:/admin-page";
	    }
	
	
	 @GetMapping("/About")

	    public String showAboutusPageabout(Model model)

	    {
		 Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
		 model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
	    	NavLink navlink = NavLinkRepository.findById(3L).orElse(null);

	        // Check if aboutus is not null
	        if (navlink != null) {
	            // Add the specific About Us data to the model
	            model.addAttribute("navlink", navlink);
	            return "Aboutus"; // Assuming "aboutus" is the name of the view for displaying About Us details
	        } else {
	            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
	            return "redirect:/Aboutus";
	}
	    
	    }


	
	@GetMapping("/aboutus/1")//footer//

    public String showAboutmymartPage(Model model)

    {

    	Aboutus aboutus = aboutusRepository.findById(1L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "aboutmymart"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/aboutmymart";
}
}
	@GetMapping("/aboutus/2")//footer//

    public String showCareersPage(Model model)

    {

    	Aboutus aboutus = aboutusRepository.findById(2L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "careers"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/careers";
}
}

	
	
	@GetMapping("/aboutus/3")

    public String showPressreleasesPage(Model model)

    {

    	Aboutus aboutus = aboutusRepository.findById(3L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "pressreleases"; // Assuming "pressreleases" is the name of the view for displaying About Us details
        } else {
            // If pressreleases is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/pressreleases";
}
}
	
	@GetMapping("/Keepintouch/1")
	public String Keepintouch() {
		return "redirect:https://www.google.com/";
	}
	@GetMapping("/Keepintouch/2")
	public String Keepintouch1() {
		return "redirect:https://in.linkedin.com/";
	}
	@GetMapping("/Keepintouch/3")
	public String Keepintouch2() {
		return "redirect:https://www.facebook.com/";
	}

	@GetMapping("/Keepintouch/4")
	public String Keepintouch3() {
		return "redirect:https://x.com/?lang=en";
	}

	@GetMapping("/Keepintouch/5")
	public String Keepintouch4() {
		return "redirect:https://www.instagram.com/";
	}
	
	
	
	@GetMapping("/aboutus/retail")

    public String showpressreleasePage(Model model)

    {

        Aboutus aboutus = aboutusRepository.findById(1L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "retail"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/index";
}
}
	
	
	@GetMapping("/consumerpolicy/1")

    public String showconsumerpolicy(Model model)

    {

        Aboutus aboutus = aboutusRepository.findById(1L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "security"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/index";
}
}
	
	@GetMapping("/consumerpolicy/2")

    public String showconsumerpolicypage(Model model)

    {

        Aboutus aboutus = aboutusRepository.findById(1L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "privacy"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/index";
}
}
	
	@GetMapping("/consumerpolicy/3")

    public String showconsumerpolicyPage(Model model)

    {

        Aboutus aboutus = aboutusRepository.findById(1L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "grievance"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/index";
}
}
	
	@GetMapping("/customersupport/1")

    public String showFeedbackPage(Model model)

    {

        Aboutus aboutus = aboutusRepository.findById(1L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "feedback"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/index";
}
}

	@GetMapping("/customersupport/2")

    public String showContactusPage(Model model)

    {

        Aboutus aboutus = aboutusRepository.findById(2L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "contactus"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/index";
}
}

	@GetMapping("/customersupport/3")

    public String showBecomeaSellerPage(Model model)

    {

        Aboutus aboutus = aboutusRepository.findById(3L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "becomeaseller"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/index";
}
}

	
	@GetMapping("/customersupport/4")

    public String showcustomersupport(Model model)

    {

        Aboutus aboutus = aboutusRepository.findById(1L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "sellerfaqs"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/index";
}
}
	
	
	@GetMapping("/conditions")

    public String showconditions(Model model)

    {

        Aboutus aboutus = aboutusRepository.findById(1L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "conditions"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/index";
}
}
	@GetMapping("/intrest")

    public String showcintrestbasedads(Model model)

    {

        Aboutus aboutus = aboutusRepository.findById(1L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "intrestbasedads"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/index";
}
}
	
	@GetMapping("/cancelreturn")

    public String showcancelreturn(Model model)

    {

        Aboutus aboutus = aboutusRepository.findById(1L).orElse(null);

        // Check if aboutus is not null
        if (aboutus != null) {
            // Add the specific About Us data to the model
            model.addAttribute("aboutus", aboutus);
            return "cancelreturn"; // Assuming "aboutus" is the name of the view for displaying About Us details
        } else {
            // If aboutus is null, redirect to a generic About Us page or handle it accordingly
            return "redirect:/index";
}
}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
//	@GetMapping("/profile")
//    public String ProfilePage() {
//    	return "login/user";
//    }
	
	
	@GetMapping("/profile")
    public String ProfilePage() {
    	return "user/user";
    }
	@GetMapping("/signout")
	 public String logout(HttpServletRequest request) {
        // Invalidate session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/"; // Redirect to the root URL (http://localhost:8080) after logout
    }
	
	
	@GetMapping("/editpopup")
	public String showEditpopup(
			Model model,@RequestParam int id
			) {
		
		try {
			Popup popup=repo.findById(id).get();
			model.addAttribute("popup", popup);
			
			PopupDto popupDto=new PopupDto();
			model.addAttribute("popupDto", popupDto);
			popupDto.setPopupdata(popup.getPopupdata());
			popupDto.setPopupofferdata(popup.getPopupofferdata());
			popupDto.setPopupmarqueedata(popup.getPopupmarqueedata());
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception is : "+e.getMessage());
			return "redirect:/popup";
		}
		
		return "popup/Editpopup";
		
	}
	
	@PostMapping("/editpopup")
	public String updatePopup(
			Model model,@RequestParam int id,
			@Valid @ModelAttribute PopupDto popupDto,
			BindingResult result
			) {
		
		try {
			
			Popup popup = repo.findById(id).get();
			model.addAttribute("popup", popup);
			
			if (result.hasErrors()) {
				return "popup/Editpopup";
				
			}
			
			//code for deleting old images
			if (!popupDto.getPopupimage().isEmpty()) {
				String uploadDir ="public/images/";
				Path oldImagePath = Paths.get(uploadDir+popup.getPopupimage());
				try {
					//deletes old image
					Files.delete(oldImagePath);
				} catch (Exception e) {
					System.out.println("Exception is : "+e.getMessage());
				}
				//Saving New Image
				MultipartFile image = popupDto.getPopupimage();
				
				String storageFileName  =image.getOriginalFilename();
				
				try(InputStream inputStream= image.getInputStream()){
					Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
							StandardCopyOption.REPLACE_EXISTING);
				}
				popup.setPopupimage(storageFileName);
				
			}
			
			popup.setPopupdata(popupDto.getPopupdata());
			popup.setPopupofferdata(popupDto.getPopupofferdata());
			popup.setPopupmarqueedata(popupDto.getPopupmarqueedata());
			
			
			repo.save(popup);
			
		} catch (Exception e) {
			System.out.println("Exception : "+e.getMessage());
		}
		
		return "redirect:/admin-page";
		
	}
	
	@GetMapping("/addpopupimage")
	public String ShowCreate(Model m) {
	PopupDto popupDto = new PopupDto();
	m.addAttribute("popupDto",popupDto);
		return "popup/createpopup";
	}
	
	
	
	
	@PostMapping("/addpopupimage")
	public String EditCreate(@Valid @ModelAttribute PopupDto popupDto,BindingResult result) {
		
		
		
		if(popupDto.getPopupimage().isEmpty()) {
			result.addError(new FieldError("popupDto","popupimage","this field is required"));
		}
		
	
		
		if(result.hasErrors()) {
			return "popup/createpopup";
		}
		
		MultipartFile image1 = popupDto.getPopupimage();
		String StoreImage = image1.getOriginalFilename();
		
		try {
			String uploadDir = "public/images/";
			Path uploadPath = Paths.get(uploadDir);
			
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			
			try (InputStream inputStream = image1.getInputStream()){
				Files.copy(inputStream,Paths.get(uploadDir+StoreImage),StandardCopyOption.REPLACE_EXISTING);
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		 Popup popup=new Popup();
		
		 popup.setPopupdata(popupDto.getPopupdata());
		 popup.setPopupofferdata(popupDto.getPopupofferdata());
		 popup.setPopupmarqueedata(popupDto.getPopupmarqueedata());
		 popup.setPopupimage(StoreImage);
		
		repo.save(popup);
		return "redirect:/admin-page";
	}
	
	
	
	@GetMapping("/deletepopupimage")
	public String deleteProduct(
			@RequestParam int id
			) {
		
		try {
			Popup popup = repo.findById(id).get(); 
			Path imagePath = Paths.get("public/images"+popup.getPopupimage());
			
			try {
				//To delete image
				Files.delete(imagePath);
				
			} catch (Exception e) {
				System.out.println("Exception : "+e.getMessage());
			}
			//To delete product except image
			repo.delete(popup);
			
		} catch (Exception e) {
			System.out.println("Exception : "+e.getMessage());
		}
		
		return "redirect:/admin-page";
		
	}

	@GetMapping("/showpopup")
    public String showPopup(Model model) {
        List<Popup> showPopup = repo.findAll();
        model.addAttribute("showPopup", showPopup);
        return "popup/showPopup";

    }

	
	}

