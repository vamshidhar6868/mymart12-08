package com.mymart.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String brand;
	
	private double price;
	
	private int ratingCount = 0;
	
	
	public int getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}




	@Column(columnDefinition="TEXT")
	private String description;
	private Date createdAt;
	

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

  
    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    
    public void addRating(Rating rating) {
        ratings.add(rating);
        rating.setProduct(this);
    }

    public void removeRating(Rating rating) {
        ratings.remove(rating);
        rating.setProduct(null);
    }
	
	
	
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
	private String imageFileName;
	
	private double discountedPrice;
	
	 @OneToOne(mappedBy = "product")
	    private Deal deal;
	
	 
	 
	public Deal getDeal() {
		return deal;
	}

	public double getDiscountedPrice() {
        if (deal != null) {
            double discountPercentage = deal.getDiscount();
            return price * (1 - discountPercentage / 100);
        } else {
            return price; // If no deal is associated, return the original price
        }
    }
	
	public void calculateDiscountedPrice() {
        if (deal != null) {
            double discountPercentage = deal.getDiscount();
            this.discountedPrice = price * (1 - discountPercentage / 100);
        } else {
            this.discountedPrice = price; // If no deal is associated, set discounted price as original price
        }
    }

	
	public void updateDiscountedPrice() {
        if (deal != null) {
            // Calculate discounted price based on deal
            double discount = deal.getDiscount();
            discountedPrice = price - (price * (discount / 100));
        } else {
            // If no deal is associated, set discounted price same as price
            discountedPrice = price;
        }
    }
	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}


	public Product(int id, String name, String brand, double price, String description, Date createdAt, Category category,
		String imageFileName, int ratingCount) {
	super();
	this.id = id;
	this.name = name;
	this.brand = brand;
	this.price = price;
	this.description = description;
	this.createdAt = createdAt;
	this.category = category;
	this.imageFileName = imageFileName;
	this.ratingCount = ratingCount;
}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Product() {
		super();
		
	}

	
}