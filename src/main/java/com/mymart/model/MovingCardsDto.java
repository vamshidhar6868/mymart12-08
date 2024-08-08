package com.mymart.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MovingCardsDto 
{
	@NotEmpty(message="this field is required")

	private String name1;
	
	@NotEmpty(message="this field is required")

	private String brand1;
	@NotEmpty(message="this field is required")

	private String description1;
	@NotEmpty(message="this field is required")

	private String price1;
	@NotNull(message="this field is required")
	private MultipartFile image1;
	
	
	@NotEmpty(message="this field is required")

	private String name2;
	
	@NotEmpty(message="this field is required")

	private String brand2;
	@NotEmpty(message="this field is required")

	private String description2;
	@NotEmpty(message="this field is required")

	private String price2;
	@NotNull(message="this field is required")
	private MultipartFile image2;
	
	
	
	
	
	@NotEmpty(message="this field is required")

	private String name3;
	@NotEmpty(message="this field is required")

	private String brand3;
	@NotEmpty(message="this field is required")

	private String description3;
	@NotEmpty(message="this field is required")

	private String price3;
	@NotNull(message="this field is required")
	private MultipartFile image3;
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getBrand1() {
		return brand1;
	}
	public void setBrand1(String brand1) {
		this.brand1 = brand1;
	}
	public String getDescription1() {
		return description1;
	}
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	public MultipartFile getImage1() {
		return image1;
	}
	public void setImage1(MultipartFile image1) {
		this.image1 = image1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getBrand2() {
		return brand2;
	}
	public void setBrand2(String brand2) {
		this.brand2 = brand2;
	}
	public String getDescription2() {
		return description2;
	}
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	public String getPrice2() {
		return price2;
	}
	public void setPrice2(String price2) {
		this.price2 = price2;
	}
	public MultipartFile getImage2() {
		return image2;
	}
	public void setImage2(MultipartFile image2) {
		this.image2 = image2;
	}
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	public String getBrand3() {
		return brand3;
	}
	public void setBrand3(String brand3) {
		this.brand3 = brand3;
	}
	public String getDescription3() {
		return description3;
	}
	public void setDescription3(String description3) {
		this.description3 = description3;
	}
	public String getPrice3() {
		return price3;
	}
	public void setPrice3(String price3) {
		this.price3 = price3;
	}
	public MultipartFile getImage3() {
		return image3;
	}
	public void setImage3(MultipartFile image3) {
		this.image3 = image3;
	}
	
}
