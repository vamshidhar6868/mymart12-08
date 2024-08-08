
  package com.mymart.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address shippingAddresses;

    @Column(nullable = false)
    private double totalAmount; // Changed to double for numerical values

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

//    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
//    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShippingMethod shippingMethod;

    @Column(nullable = false)
    private String deliveryTime = "2-3 business days";
    @Column(nullable = true)
    private String paymentTransactionId; // Unique ID for payment transaction

    @Column(nullable = false, unique = true)
    private String orderNumber; // Add order number attribute
   
    @Column(nullable = false)
    private double shippingCharges; // Add shipping charges attribute

    private String amount; 
    @Column(nullable = false)
    private double subtotal;
    
    public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public double getShippingCharges() {
		return shippingCharges;
	}

	public void setShippingCharges(double shippingCharges) {
		this.shippingCharges = shippingCharges;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	// Constructors, getters, and setters
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	


	public Address getShippingAddresses() {
		return shippingAddresses;
	}

	public void setShippingAddresses(Address shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

//	public Payment getPayment() {
//		return payment;
//	}
//
//	public void setPayment(Payment payment) {
//		this.payment = payment;
//	}

	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(ShippingMethod shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaymentTransactionId() {
		return paymentTransactionId;
	}

	public void setPaymentTransactionId(String paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}

	
	public Orders() {
        this.orderDate = LocalDateTime.now();
    }
}



//package com.mymart.model;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//
//
//@Entity
//@Table(name = "orders")
//public class Orders {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<OrderItem> orderItems = new ArrayList<>();
//
//    @ManyToOne
//    @JoinColumn(name = "address_id")
//    private Address shippingAddresses;
//
//    @Column(nullable = false)
//    private double totalAmount;
//
//    @Column(nullable = false)
//    private LocalDateTime orderDate;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private OrderStatus status;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private ShippingMethod shippingMethod;
//
//    @Column(nullable = false)
//    private String deliveryTime = "2-3 business days";
//
//    @Column(nullable = true)
//    private String paymentTransactionId;
//
//    @Column(nullable = false, unique = true)
//    private String orderNumber;
//
//    @Column(nullable = false)
//    private double shippingCharges;
//
//    private String amount;
//    @Column(nullable = false)
//    private double subtotal;
//
//    public String getAmount() {
//		return amount;
//	}
//
//	public void setAmount(String amount) {
//		this.amount = amount;
//	}
//	
//    // Getters and Setters
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public List<OrderItem> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//    }
//
//    public Address getShippingAddresses() {
//        return shippingAddresses;
//    }
//
//    public void setShippingAddresses(Address shippingAddresses) {
//        this.shippingAddresses = shippingAddresses;
//    }
//
//    public double getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(double totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//
//    public LocalDateTime getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(LocalDateTime orderDate) {
//        this.orderDate = orderDate;
//    }
//
//    public OrderStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(OrderStatus status) {
//        this.status = status;
//    }
//
//    public ShippingMethod getShippingMethod() {
//        return shippingMethod;
//    }
//
//    public void setShippingMethod(ShippingMethod shippingMethod) {
//        this.shippingMethod = shippingMethod;
//    }
//
//    public String getDeliveryTime() {
//        return deliveryTime;
//    }
//
//    public void setDeliveryTime(String deliveryTime) {
//        this.deliveryTime = deliveryTime;
//    }
//
//    public String getPaymentTransactionId() {
//        return paymentTransactionId;
//    }
//
//    public void setPaymentTransactionId(String paymentTransactionId) {
//        this.paymentTransactionId = paymentTransactionId;
//    }
//
//    public String getOrderNumber() {
//        return orderNumber;
//    }
//
//    public void setOrderNumber(String orderNumber) {
//        this.orderNumber = orderNumber;
//    }
//
//    public double getShippingCharges() {
//        return shippingCharges;
//    }
//
//    public void setShippingCharges(double shippingCharges) {
//        this.shippingCharges = shippingCharges;
//    }
//
//    public double getSubtotal() {
//        return subtotal;
//    }
//
//    public void setSubtotal(double subtotal) {
//        this.subtotal = subtotal;
//    }
//}