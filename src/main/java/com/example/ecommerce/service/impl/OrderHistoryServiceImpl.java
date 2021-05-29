package com.example.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.OrderDetailsResponseDto;
import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.entity.OrderDetails;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.APIException;
import com.example.ecommerce.repository.OrderHistoryRepository;
import com.example.ecommerce.repository.OrdersRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.OrderHistoryService;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService{

	@Autowired
	private OrderHistoryRepository orderHistoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Override
	public List<OrderDetailsResponseDto> getOrderHistory(int userId) {
	   
		Optional<User> user = this.userRepository.findById(userId);
		
		if(!user.isPresent())
			throw new APIException("Invalid User Id");
		List<OrderDetailsResponseDto> orderDetails = new ArrayList<>();
		List<OrderDetails> source = this.orderHistoryRepository.getOrdersByUserId(userId);
		Map<Integer, OrderDetailsResponseDto> orderDetailsMap = new HashMap<>();
		for(OrderDetails od : source) {
			
			OrderDetailsResponseDto odrd = null;
			if(orderDetailsMap.get(od.getOrders().getOrderId()) == null)
			{
				odrd = new OrderDetailsResponseDto();
				odrd.setOrderId(od.getOrders().getOrderId());
				odrd.setOrderDate(od.getOrders().getOrderDate());
				
				orderDetailsMap.put(od.getOrders().getOrderId(), odrd);
			}else 
			{
				odrd = orderDetailsMap.get(od.getOrders().getOrderId());
			}
			
			ProductResponseDto p = new ProductResponseDto();
			p.setProductId(od.getProduct().getProductId());
			p.setProductName(od.getProduct().getProductName());
			p.setProductPrice(od.getPrice());
			p.setProductQuantity(od.getQuantity());
			
			odrd.addProducts(p);
			
		}
		for(Map.Entry<Integer, OrderDetailsResponseDto> m : orderDetailsMap.entrySet())
		{
			orderDetails.add(m.getValue());
		}
		return orderDetails;
	}

}
