package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="productcategory")
public class ProductCategory {

	@Id
	
	private int id ;
	private String allproducts;
	private String mobiles;
	private String electronics;
	private String fashions;
	private String furniture;
	private String footware; 
	private String grocery;
	private String stationary;
	private String personalcare;
	private String food;
	private String games;
	private String toys;
	private String gifts;

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", allproducts=" + allproducts + ", mobiles=" + mobiles + ", electronics="
				+ electronics + ", fashions=" + fashions + ", furniture=" + furniture + ", footware=" + footware
				+ ", grocery=" + grocery + ", stationary=" + stationary + ", personalcare=" + personalcare + ", food="
				+ food + ", games=" + games + ", toys=" + toys + ", gifts=" + gifts + "]";
	}
	public ProductCategory(int id, String allproducts, String mobiles, String electronics, String fashions,
			String furniture, String footware, String grocery, String stationary, String personalcare, String food,
			String games, String toys, String gifts) {
		super();
		this.id = id;
		this.allproducts = allproducts;
		this.mobiles = mobiles;
		this.electronics = electronics;
		this.fashions = fashions;
		this.furniture = furniture;
		this.footware = footware;
		this.grocery = grocery;
		this.stationary = stationary;
		this.personalcare = personalcare;
		this.food = food;
		this.games = games;
		this.toys = toys;
		this.gifts = gifts;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getGames() {
		return games;
	}
	public void setGames(String games) {
		this.games = games;
	}
	public String getToys() {
		return toys;
	}
	public void setToys(String toys) {
		this.toys = toys;
	}
	public String getGifts() {
		return gifts;
	}
	public void setGifts(String gifts) {
		this.gifts = gifts;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAllproducts() {
		return allproducts;
	}
	public void setAllproducts(String allproducts) {
		this.allproducts = allproducts;
	}
	public String getMobiles() {
		return mobiles;
	}
	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	public String getElectronics() {
		return electronics;
	}
	public void setElectronics(String electronics) {
		this.electronics = electronics;
	}
	public String getFashions() {
		return fashions;
	}
	public void setFashions(String fashions) {
		this.fashions = fashions;
	}
	public String getFurniture() {
		return furniture;
	}
	public void setFurniture(String furniture) {
		this.furniture = furniture;
	}
	public String getFootware() {
		return footware;
	}
	public void setFootware(String footware) {
		this.footware = footware;
	}
	public String getGrocery() {
		return grocery;
	}
	public void setGrocery(String grocery) {
		this.grocery = grocery;
	}
	public String getStationary() {
		return stationary;
	}
	public void setStationary(String stationary) {
		this.stationary = stationary;
	}
	public String getPersonalcare() {
		return personalcare;
	}
	public void setPersonalcare(String personalcare) {
		this.personalcare = personalcare;
	}
	
	
}
