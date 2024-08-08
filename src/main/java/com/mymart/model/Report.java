package com.mymart.model;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="report")
public class Report {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	private String message;
	
	public Report(long id, String name, String email, String message, long userId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.message = message;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", name=" + name + ", email=" + email + ", message=" + message + "]";
	}
	
	
	

}
