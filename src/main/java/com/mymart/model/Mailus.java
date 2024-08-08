package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Mailus")
public class Mailus {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    private String mailus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMailus() {
		return mailus;
	}

	public void setMailus(String mailus) {
		this.mailus = mailus;
	}

	public Mailus(long id, String mailus) {
		super();
		this.id = id;
		this.mailus = mailus;
	}

	public Mailus() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Mailus [id=" + id + ", mailus=" + mailus + "]";
	}
    
    
}
