package com.mymart.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mymart.model.MovingCards;
import com.mymart.model.MovingCardsDto;
import com.mymart.repository.MovingCardsRepository;

import jakarta.validation.Valid;

@Controller
public class MovingCardsController {

	@Autowired
	private MovingCardsRepository cardrepo;
	
	@GetMapping("/createcard")
	public String ShowCardCreatepage(Model m) {
	MovingCardsDto cardDto = new MovingCardsDto();
	m.addAttribute("cardDto",cardDto);
		return "movingcard/createcards";
	}
	
	
	
	@PostMapping("/createcard")
	public String EditCreate(@Valid @ModelAttribute MovingCardsDto cardDto,BindingResult result) {
		
		
		
		if(cardDto.getImage1().isEmpty()) {
			result.addError(new FieldError("cardDto","image1","this field is required"));
		}
		
		if(cardDto.getImage2().isEmpty()) {
			result.addError(new FieldError("cardDto","image2","this field is required"));
		}
		
		if(cardDto.getImage3().isEmpty()) {
			result.addError(new FieldError("cardDto","image3","this field is required"));
		}
		
		if(result.hasErrors()) {
			return "movingcard/createcards";
		}
		
		MultipartFile image1 = cardDto.getImage1();
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
		
		
		
		
		
		MultipartFile image2 = cardDto.getImage2();
		String StoreImage2 = image2.getOriginalFilename();
		
		try {
			String uploadDir = "public/images/";
			Path uploadPath = Paths.get(uploadDir);
			
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			
			try (InputStream inputStream = image2.getInputStream()){
				Files.copy(inputStream,Paths.get(uploadDir+StoreImage2),StandardCopyOption.REPLACE_EXISTING);
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		
		
		
		MultipartFile image3 = cardDto.getImage3();
		String StoreImage3 = image3.getOriginalFilename();
		
		try {
			String uploadDir = "public/images/";
			Path uploadPath = Paths.get(uploadDir);
			
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			
			try (InputStream inputStream = image3.getInputStream()){
				Files.copy(inputStream,Paths.get(uploadDir+StoreImage3),StandardCopyOption.REPLACE_EXISTING);
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		MovingCards card= new MovingCards();
		card.setDescription1(cardDto.getDescription1());
		card.setName1(cardDto.getName1());
		card.setBrand1(cardDto.getBrand1());
		card.setPrice1(cardDto.getPrice1());
		card.setImage1(StoreImage);
		
		card.setDescription2(cardDto.getDescription2());
		card.setName2(cardDto.getName2());
		card.setBrand2(cardDto.getBrand2());
		card.setPrice2(cardDto.getPrice2());
		card.setImage2(StoreImage2);
		
		
		card.setDescription3(cardDto.getDescription3());
		card.setName3(cardDto.getName3());
		card.setBrand3(cardDto.getBrand3());
		card.setPrice3(cardDto.getPrice3());
		card.setImage3(StoreImage3);
		
		cardrepo.save(card);
		return "redirect:/admin-page";
	}
	
	
	
}
