package com.mymart.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mymart.model.DropdownItem;
import com.mymart.model.MovingCards;
import com.mymart.model.NavLink;
import com.mymart.model.User;
import com.mymart.model.UserDto;
import com.mymart.repository.MovingCardsRepository;
import com.mymart.repository.UserRepository;
import com.mymart.service.NavBarService;
import com.mymart.service.UserService;
import com.mymart.service.UserServiceImpl;

import jakarta.validation.Valid;

@Controller
public class loginController {
	
	@Autowired
	private UserServiceImpl serviceImpl; 
	@Autowired
	private final NavBarService service; 
	@Autowired
	private MovingCardsRepository cardrepo;
	@Autowired

	UserRepository userrepo;
    public loginController(NavBarService service) {
        this.service = service;
    }

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserServiceImpl userServiceimpl;
	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
		return "login/register";
	}
	

	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, Model model,
			@RequestParam(name="g-recaptcha-response") String captcha) {
		 if (bindingResult.hasErrors()) {
	            // Handle validation errors, return appropriate response
		        model.addAttribute("message", "Invalid email or other validation error.");
	        }
	    if (userServiceimpl.existsByEmail(userDto.getEmail())) {
	        model.addAttribute("message", "There is already an account registered with this email.");
	    } else if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
	        model.addAttribute("message", "Password and confirm password should be same");
	    } else {
	    	if (userServiceimpl.validateCaptcha(captcha)) {
	    		
	    		userService.save(userDto);
		        model.addAttribute("successMessage", "You have Registered Successfully!");
			}
	    	else if(!userServiceimpl.validateCaptcha(captcha)) {
	    		 model.addAttribute("message", "Please verify captcha");
	    		   
	    	}
	      }

	    return "login/register";
	}

	@GetMapping("/login")
	public String login() {
		
		return "login/login";
	}
	
	
	@GetMapping("user-page")
	public String userPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		//return "login/user";
		return "redirect:/";
	}
	
	

//	@GetMapping("admin-page")
//	public String adminPage (Model model, Principal principal) {
//		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//		
//		 List<NavLink> allNavLinks = service.getAllNavLinks();
//	        Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();
//
//	       
//
//	        model.addAttribute("allNavLinks", allNavLinks);
//	        model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
//		
//	        List<MovingCards> card=cardrepo.findAll();
//			model.addAttribute("card",card);
//		model.addAttribute("user", userDetails);
//		return "login/admin";
//	}
	
	
	
	
	
	@GetMapping("admin-page")
	public String adminPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		
		 List<NavLink> allNavLinks = service.getAllNavLinks();
	        Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();

	       

	        model.addAttribute("allNavLinks", allNavLinks);
	        model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
		
		
		model.addAttribute("user", userDetails);
		return "login/adminPage";
	}
	
	@GetMapping("admin-page/usereditpage")
	public String usereditpage(Model model) {
//		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());

		 List<NavLink> allNavLinks = service.getAllNavLinks();
	        Map<NavLink, List<DropdownItem>> navbarWithDropdownData = service.getNavbarWithDropdownData();

	       

	        model.addAttribute("allNavLinks", allNavLinks);
	        model.addAttribute("navbarWithDropdownData", navbarWithDropdownData);
	        
	        List<MovingCards> card=cardrepo.findAll();
			model.addAttribute("card",card);
			

//	        List<MovingCards> card=cardrepo.findAll();
//			model.addAttribute("card",card);
//		model.addAttribute("user", userDetails);
			return "login/admin";
	}

	
	
	
	@GetMapping("/profiles")
	public String getUser(@RequestParam String name, Model model) {


		try {
			User user = userrepo.findByName(name);
			model.addAttribute("user", user);
			UserDto userDto = new UserDto();
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setContactNo(user.getContactNo());
		} catch (Exception e) {
			System.out.println("Exception is :" + e.getMessage());

		}

		return "user/myprofile";
	}

	@GetMapping("/edit")
	public String editUser(@RequestParam String name, Model model) {
		try {
			User user = userrepo.findByName(name); // Fetch user by ID
			model.addAttribute("user", user);
			UserDto userDto = new UserDto();
			model.addAttribute("userDto", userDto);


			userDto.setName(user.getName());

			userDto.setEmail(user.getEmail());
			userDto.setContactNo(user.getContactNo());
			// Pass any additional model attributes needed for editing
		} catch (Exception e) {
			System.out.println("Exception is :" + e.getMessage());
			// Handle error appropriately
		}
		return "user/editprofile"; // Return the edit profile HTML template
	}



	
	@PostMapping("/edit")
	public String saveUser(@ModelAttribute UserDto userDto, Model model, RedirectAttributes redirectAttributes) {
	    try {
	        // Fetch the current user from the database
	        User currentUser = userrepo.findById(userServiceimpl.getCurrentUserId()).orElse(null);

	        if (currentUser != null) {
	            // Check if the edited email is already used by another user
	            User existingUserWithEmail = userrepo.findByEmail(userDto.getEmail());

	            if (existingUserWithEmail != null && !existingUserWithEmail.getId().equals(currentUser.getId())) {
	                // Email is already used by another user, show error message and stay on the same edit page
	            	 model.addAttribute("message", "This Email is already used by another User.");	                
	                // Fetch user again to populate the form with existing data
	                User user = userrepo.findById(currentUser.getId()).orElse(null);
	                model.addAttribute("user", user);
	                model.addAttribute("userDto", userDto);
	                return "user/editprofile"; // Return the edit profile HTML template
	            }

	            // Update user details with the values from userDto
	            currentUser.setEmail(userDto.getEmail());
	            currentUser.setContactNo(userDto.getContactNo());

	            // Save the updated user
	            userrepo.save(currentUser);
	        }
	    } catch (Exception e) {
	        // Handle exceptions, log errors, etc.
	        System.out.println("Exception occurred: " + e.getMessage());
	        redirectAttributes.addFlashAttribute("error", "An error occurred while saving user details.");
	    }

	    // Redirect to some page after successful submission
	    return "redirect:/login"; // Redirect to the admin page or any other page
	}
	@GetMapping("/count")
    public ResponseEntity<Long>GetCountUser()
    {
          long totalUsers=userService.GetCountUser();
       return ResponseEntity.ok(totalUsers);

    }

}

