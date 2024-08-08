package com.mymart.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymart.model.Orders;
import com.mymart.model.User;
import com.mymart.model.Address;
import com.mymart.model.CartItem;
import com.mymart.model.OrderItem;
import com.mymart.model.OrderStatus;
import com.mymart.repository.OrderItemRepository;
import com.mymart.repository.OrderRepository;
import com.mymart.repository.UserRepository;
import com.razorpay.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class OrderService {
	
	 @PersistenceContext
     private EntityManager entityManager;
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService  productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartItemService cartItemService;
    
    @Autowired
    private UserService userservice;
    

    public Orders createOrder(Orders order) {
        calculateTotalAmount(order); // Calculate total amount based on order items
        setDeliveryEstimate(order); // Set delivery estimate based on shipping method
        generateOrderNumber(order); // Generate a unique order number
        updateOrderStatus(order); // Set initial status for the order
        sendConfirmationEmail(order); // Send confirmation email to the user
        // You can add more logic here as needed

        return orderRepository.save(order);
    }

    private void calculateTotalAmount(Orders order) {
        // Logic to calculate total amount based on order items
        double totalAmount = 0.0;
        for (OrderItem orderItem : order.getOrderItems()) {
            totalAmount += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }
        order.setTotalAmount(totalAmount);
    }

    private void setDeliveryEstimate(Orders order) {
        // Logic to set delivery estimate based on shipping method (e.g., standard, express, etc.)
        // This can involve estimating delivery date based on shipping provider APIs or predefined rules
        order.setDeliveryTime("2-3 business days"); // Example delivery estimate
    }

    private void generateOrderNumber(Orders order) {
        // Generate a unique order number using UUID
        String orderNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        order.setOrderNumber(orderNumber);
    }

    private void updateOrderStatus(Orders order) {
        // Set initial status for the order (e.g., PLACED)
        order.setStatus(OrderStatus.PLACED);
    }

    private void sendConfirmationEmail(Orders order) {
        // Logic to send confirmation email to the user with order details
        // You can use email sending libraries or APIs here
        // Example: EmailService.sendConfirmationEmail(order.getUser().getEmail(), "Order Confirmation", "Your order details: " + order.toString());
    }





	 public Orders getOrderForConfirmation(int orderId) {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.orElse(null);
    }





	public void saveOrder(Orders order) {
		orderRepository.save(order);
		
	}

	 public List<Orders> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }


	 public List<Orders> getAllOrders() {
	        return orderRepository.findAll();
	    }

	    public Orders getOrderById(int orderId) {
	        Optional<Orders> optionalOrder = orderRepository.findById(orderId);
	        return optionalOrder.orElse(null);
	    }

	    public void updateOrder(Orders order) {
	        orderRepository.save(order);
	    }





		public Orders findByOrderNumber(String orderId) {
			// TODO Auto-generated method stub
			 return orderRepository.findByOrderNumber(orderId);
		}





		public long GetTotalOrder()
        {
            return orderRepository.count();

        }


		public Orders getOrderByOrderNumber(String orderNumber) {
	        return orderRepository.findByOrderNumber(orderNumber);
	    }

		 public Orders getLatestOrderForUser(User user) {
        List<Orders> orders = orderRepository.findByUserOrderByOrderDateDesc(user);
        return orders.isEmpty() ? null : orders.get(0);
    }


}


	






		
	


	


	
