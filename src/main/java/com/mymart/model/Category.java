package com.mymart.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Table
@Entity
public class Category {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    @NotEmpty
	    private String name;

	    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	    private List<Product> products = new ArrayList<>();

		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
			this.products = products;
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

		public Category(int id, @NotEmpty String name) {
			super();
			this.id = id;
			this.name = name;
		}


		@Override
		public String toString() {
			return "Category [name=" + name + "]";
		}

		public Category() {
			super();
			// TODO Auto-generated constructor stub
		}
    
	    
}



