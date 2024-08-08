package com.mymart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymart.model.OrderItem;
import com.mymart.model.Orders;
import com.mymart.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;
	 public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

		//Total sales Count

	     @Autowired
	        public OrderItemService(OrderItemRepository orderItemRepository) {
	            this.orderItemRepository = orderItemRepository;
	        }

	        public Double getTotalSales() {
	            return orderItemRepository.getTotalSales();
	        }
	        //Top selling products
	        public List<Object[]> getTopSellingProducts() {
	            return orderItemRepository.findTopSellingProducts();
	        }

			 public List<OrderItem> findByOrder(Orders order) {
        return orderItemRepository.findByOrder(order);
    }

			


}
