package com.example.ecommerce.service;

import com.example.ecommerce.dto.BuyRequestDto;
import com.example.ecommerce.dto.BuyResponseDto;

public interface BuyService {

	public BuyResponseDto createOrder(BuyRequestDto buyRequestDto);

}
