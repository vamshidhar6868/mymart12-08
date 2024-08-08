package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Consumerpolicy")
public class Consumerpolicy
{
    @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private long id;
     
     private String Consumerpolicy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConsumerpolicy() {
        return Consumerpolicy;
    }

    public void setConsumerpolicy(String consumerpolicy) {
        Consumerpolicy = consumerpolicy;
    }

    public Consumerpolicy(long id, String consumerpolicy) {
        super();
        this.id = id;
        Consumerpolicy = consumerpolicy;
    }

    public Consumerpolicy() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Consumerpolicy [id=" + id + ", Consumerpolicy=" + Consumerpolicy + "]";
    }
     
      
}
