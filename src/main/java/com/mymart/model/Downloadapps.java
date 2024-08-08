package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Downloadapps")
public class Downloadapps {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String googleplaystore;
	private String applestore;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoogleplaystore() {
		return googleplaystore;
	}
	public void setGoogleplaystore(String googleplaystore) {
		this.googleplaystore = googleplaystore;
	}
	public String getApplestore() {
		return applestore;
	}
	public void setApplestore(String applestore) {
		this.applestore = applestore;
	}
	public Downloadapps(Long id, String googleplaystore, String applestore) {
		super();
		this.id = id;
		this.googleplaystore = googleplaystore;
		this.applestore = applestore;
	}
	public Downloadapps() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Downloadapps [id=" + id + ", googleplaystore=" + googleplaystore + ", applestore=" + applestore + "]";
	}
	
	
	
}
