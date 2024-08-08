package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Roaddress")
public class Roaddress {
	 @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private long id;
     
     private String roaddress;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoaddress() {
		return roaddress;
	}

	public void setRoaddress(String roaddress) {
		this.roaddress = roaddress;
	}

	public Roaddress(long id, String roaddress) {
		super();
		this.id = id;
		this.roaddress = roaddress;
	}

	public Roaddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Roaddress [id=" + id + ", roaddress=" + roaddress + "]";
	}
     
}
