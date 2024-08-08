/*
 * package com.mymart.model;
 * 
 * import jakarta.persistence.Column; import jakarta.persistence.Entity; import
 * jakarta.persistence.GeneratedValue; import
 * jakarta.persistence.GenerationType; import jakarta.persistence.Id; import
 * jakarta.persistence.JoinColumn; import jakarta.persistence.ManyToOne;
 * 
 * @Entity public class OrderItem {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "order_id", nullable = false) private Orders order;
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "product_id", nullable = false) private Product product;
 * 
 * @Column(nullable = false) private int quantity;
 * 
 * @Column(nullable = false) private double totalPrice;
 * 
 * public double getTotalPrice() { return totalPrice; }
 * 
 * public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice;
 * }
 * 
 * public int getId() { return id; }
 * 
 * public void setId(int id) { this.id = id; }
 * 
 * public Orders getOrder() { return order; }
 * 
 * public void setOrder(Orders order) { this.order = order; }
 * 
 * public Product getProduct() { return product; }
 * 
 * public void setProduct(Product product) { this.product = product; }
 * 
 * public int getQuantity() { return quantity; }
 * 
 * public void setQuantity(int quantity) { this.quantity = quantity; }
 * 
 * 
 * }
 */

package com.mymart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double totalPrice;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
