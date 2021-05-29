package com.example.ecommerce.service;

import java.util.List;

import com.example.ecommerce.dto.OrderDetailsResponseDto;
import com.example.ecommerce.entity.OrderDetails;

public interface OrderHistoryService {

	public List<OrderDetailsResponseDto> getOrderHistory(int userId); 
	

}
