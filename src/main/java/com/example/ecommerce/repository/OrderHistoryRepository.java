package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ecommerce.entity.OrderDetails;

public interface OrderHistoryRepository extends JpaRepository<OrderDetails, Integer> {

	
	@Query("from OrderDetails od where orders.user.userId =:userId")
	public List<OrderDetails> getOrdersByUserId(int userId);
}
