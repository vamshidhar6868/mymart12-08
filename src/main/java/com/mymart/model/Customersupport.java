package com.mymart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Customersupport")
public class Customersupport
{
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private long id;
     
     private String customersupport;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomersupport() {
        return customersupport;
    }

    public void setCustomersupport(String customersupport) {
        this.customersupport = customersupport;
    }

    public Customersupport(long id, String customersupport) {
        super();
        this.id = id;
        this.customersupport = customersupport;
    }

    public Customersupport() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Customersupport [id=" + id + ", customersupport=" + customersupport + "]";
    }
     
     

}
