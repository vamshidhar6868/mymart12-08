package com.mymart;

public class ProductFilter {

    private String name;
       private String brand;
       private String category;
       private Double price;

       public ProductFilter() {
       }

       public ProductFilter(String name, String brand) {
           this.name = name;
           this.brand = brand;
       }

       public void setPrice(Double price) {
           this.price = price;
       }

       public ProductFilter(String name, String brand, String category, Double price) {
           super();
           this.name = name;
           this.brand = brand;
           this.category = category;
           this.price = price;
       }

       public String getCategory() {
           return category;
       }

       public void setCategory(String category) {
           this.category = category;
       }

       public Double getPrice() {
           return price;
       }



       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public String getBrand() {
           return brand;
       }

       public void setBrand(String brand) {
           this.brand = brand;
       }
}
