package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="Aboutus")
public class Aboutus 
{
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private long id;
     
     private String aboutus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public Aboutus(long id, String aboutus) {
        super();
        this.id = id;
        this.aboutus = aboutus;
    }

    public Aboutus() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Aboutus [id=" + id + ", aboutus=" + aboutus + "]";
    }
     
     
}