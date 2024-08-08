package com.mymart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymart.model.CarouselImage;
import com.mymart.repository.CarouselImageRepository;


@Service
public class CarouselImageService {
	
	@Autowired
	 private CarouselImageRepository imageRepository;
	


	    public List<CarouselImage> getAllImages() {
	        return imageRepository.findAll();
	    }
	    
	   

}
