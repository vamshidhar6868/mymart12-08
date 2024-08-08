/*
 * package com.mymart.model;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
 * 
 * @Entity public class CarouselImage {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
 * 
 * private String imageUrl;
 * 
 * public CarouselImage() { super(); // TODO Auto-generated constructor stub }
 * 
 * public CarouselImage(int id, String imageUrl) { super(); this.id = id;
 * this.imageUrl = imageUrl; }
 * 
 * public int getId() { return id; }
 * 
 * public void setId(int id) { this.id = id; }
 * 
 * public String getImageUrl() { return imageUrl; }
 * 
 * public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
 * 

 * }
 */


package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CarouselImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String imageUrl;
    
    private String category; 
    
    public CarouselImage() {
        super();
    }
    
    public CarouselImage(int id, String imageUrl, String category) {
        super();
        this.id = id;
        this.imageUrl = imageUrl;
        this.category = category;
    }
    
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
}

