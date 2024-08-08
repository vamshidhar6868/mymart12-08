package com.mymart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mymart.model.OrderItem;
import com.mymart.model.Orders;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	//Query to count total sales
    @Query("SELECT SUM(oi.totalPrice) FROM OrderItem oi")
    double getTotalSales();
    //TopSelling Products

    @Query("SELECT p.name , SUM(oi.quantity) AS totalQuantity, SUM(oi.totalPrice) AS totalAmount " +
               "FROM OrderItem oi " +
               "JOIN oi.product p " +
               "GROUP BY oi.product " +
               "ORDER BY totalQuantity DESC, totalAmount DESC")
        List<Object[]> findTopSellingProducts();

	List<OrderItem> findByOrder(Orders order);
}
