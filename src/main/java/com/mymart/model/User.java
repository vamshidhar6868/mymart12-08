package com.mymart.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
	
public class User {

	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String name;
		private String email;
		private String password;
		private String role;
		
	    private String contactNo;
		
	  
	    
	    @OneToMany(mappedBy = "user")
	    private List<Orders> orders;
	    
	    
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    private List<Address> addresses = new ArrayList<>();

		
		public List<Address> getAddresses() {
			return addresses;
		}

		public void setAddresses(List<Address> addresses) {
			this.addresses = addresses;
		}

		public List<Orders> getOrders() {
			return orders;
		}




		public void setOrders(List<Orders> orders) {
			this.orders = orders;
		}




		public User() {
			super();
		}

		


		public String getName() {
			return name;
		}




		public void setName(String name) {
			this.name = name;
		}




		public String getContactNo() {
			return contactNo;
		}



		public void setContactNo(String contactNo) {
			this.contactNo = contactNo;
		}



		


		public User(Long id, String email, String password, String role, String contactNo) {
			super();
			this.id = id;
			this.email = email;
			this.password = password;
			this.role = role;
			this.contactNo = contactNo;
		}




		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		
		
		
}
