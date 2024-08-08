package com.mymart.service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.mymart.model.ReCaptcha;
import com.mymart.model.CartItem;
import com.mymart.model.User;
import com.mymart.model.UserDto;
import com.mymart.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private static final String GOOGLE_RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";
    
	private final String RECAPTCHA_SECRET = "6LfAc7ApAAAAAOM42fR7HnwTvZOqPaFxgZlsInnc";

	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartItemService cartService;

	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Override
	public User save(UserDto userDto) {
	    if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
	        throw new IllegalArgumentException("Passwords do not match.");
	    }

	    User user = new User();
	    user.setName(userDto.getName()); // Save the user's name
	    user.setEmail(userDto.getEmail());
	    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
	    user.setRole("USER");
	    user.setContactNo(userDto.getContactNo());
	    
	    
	    
	    sendWelcomeEmail(user.getEmail(), user.getName());
	    
	    return userRepository.save(user);
	}


	@Override
	public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		 return userRepository.findByEmail(email);
	}

	@Override
	public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found.");
        }
        String email = authentication.getName(); // Get the email of the authenticated user
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalStateException("User not found.");
        }
        return user;
    }


	@Override
	public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
	@Override
	public boolean login(String username, String password) {
        User user = userRepository.findByEmail(username);
        if (user != null && user.getPassword().equals(password)) {
            return true; // Login successful
        }
        return false; // Invalid credentials
    }
//	@Override
//	public List<CartItem> getCartItems(User user) {
//        return cartService.getCartItemsByUser(user);
//    }
	


	@Override
	public List<CartItem> getCartItems(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String userEmail = authentication.getName(); // Assuming the user's email is used as the username
            User user = userRepository.findByEmail(userEmail); // Assuming findByEmail() method exists in UserRepository
            if (user != null) {
                return user.getId();
            }
        }
        throw new IllegalStateException("No authenticated user found.");
    }


	@Override
	public User getUserByEmail(String username) {
        return userRepository.findByEmail(username);
    }


	@Override
	public User getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return (User) authentication.getPrincipal();
        }
        return null; // Return null or handle the case when no authenticated user is found
    }


	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElse(null);
	}
	
	
	//method to send Welcome email to new User after Sign up
		public void sendWelcomeEmail(String toMail, String name) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("tvsnreddysai@gmail.com");
			message.setTo(toMail);
			message.setSubject("Welcome to Our E-commerce Website!");
			message.setText("Dear " + name
					+ ",\n\nThank you for signing up with our website! We're excited to have you on board. Explore our wide range of products and enjoy your shopping experience.\n\nHappy Shopping,\nThe My Mart Team");

			mailSender.send(message);
			
		}
		
		
		public boolean validateCaptcha(String captchaResponse){
	        RestTemplate restTemplate = new RestTemplate();

	        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
	        requestMap.add("secret", RECAPTCHA_SECRET);
	        requestMap.add("response", captchaResponse);

	       ReCaptcha apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT, requestMap, ReCaptcha.class);
	       
	        if(apiResponse == null){
	            return false;
	        }

	        return Boolean.TRUE.equals(apiResponse.isSuccess());
	    }


		@Override
		public User save(User user) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public long GetCountUser() {

		    return userRepository.count();
		}





	


}
