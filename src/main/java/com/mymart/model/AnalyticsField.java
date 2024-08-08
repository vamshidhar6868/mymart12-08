package com.mymart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "analytics_fields")
public class AnalyticsField {
	
	 public AnalyticsField() {
	    }

	    @Override
	    public String toString() {
	        return "AnalyticsField{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", description='" + description + '\'' +
	                '}';
	    }

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    public AnalyticsField(Long id, String name, String description) {
	        this.id = id;
	        this.name = name;
	        this.description = description;
	    }

	    @Column(nullable = false)
	    private String name;

	    @Column(nullable = false)
	    private String description;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }
	}


