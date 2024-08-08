package com.mymart.model;

import jakarta.persistence.*;

@Entity
@Table(name="movingcard")
public class MovingCards 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name1;
	private String brand1;
	private String description1;
	private String price1;
	private String image1;
	private String name2;
	private String brand2;
	private String description2;
	private String price2;
	private String image2;
	private String name3;
	private String brand3;
	private String description3;
	private String price3;
	private String image3;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
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
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
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
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public MovingCards(int id, String name1, String brand1, String description1, String price1, String image1,
			String name2, String brand2, String description2, String price2, String image2, String name3, String brand3,
			String description3, String price3, String image3) {
		super();
		this.id = id;
		this.name1 = name1;
		this.brand1 = brand1;
		this.description1 = description1;
		this.price1 = price1;
		this.image1 = image1;
		this.name2 = name2;
		this.brand2 = brand2;
		this.description2 = description2;
		this.price2 = price2;
		this.image2 = image2;
		this.name3 = name3;
		this.brand3 = brand3;
		this.description3 = description3;
		this.price3 = price3;
		this.image3 = image3;
	}
	public MovingCards() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MovingCards [id=" + id + ", name1=" + name1 + ", brand1=" + brand1 + ", description1=" + description1
				+ ", price1=" + price1 + ", image1=" + image1 + ", name2=" + name2 + ", brand2=" + brand2
				+ ", description2=" + description2 + ", price2=" + price2 + ", image2=" + image2 + ", name3=" + name3
				+ ", brand3=" + brand3 + ", description3=" + description3 + ", price3=" + price3 + ", image3=" + image3
				+ "]";
	}
	
}
