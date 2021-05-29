package com.example.ecommerce.controller;

import java.util.List;

import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.OrderDetailsResponseDto;
import com.example.ecommerce.entity.OrderDetails;
import com.example.ecommerce.service.OrderHistoryService;


@RestController
@RequestMapping("/orders")
public class OrderHistoryController {

	private static final Logger logger = LoggerFactory.getLogger(OrderHistoryController.class);
	
	@Autowired
	private OrderHistoryService OrderHistoryService;
	
	@GetMapping("/{id}")
	public List<OrderDetailsResponseDto> getOrderHistory(@PathVariable(value = "id") int userId) {
		logger.info("In method : getOrderHistory");	
		return this.OrderHistoryService.getOrderHistory(userId);
	}
}
