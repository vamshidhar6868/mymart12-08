package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CardItem { 
	

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
	    private String imageUrl;
	    private String title;
	    
	    
		public CardItem() {
			super();
			// TODO Auto-generated constructor stub
		}


		public CardItem(int id, String imageUrl, String title) {
			super();
			this.id = id;
			this.imageUrl = imageUrl;
			this.title = title;
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


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}
	    
	    
        

}
