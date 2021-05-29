package com.example.ecommerce.service;

import java.util.List;

import com.example.ecommerce.dto.SearchResponseDto;

public interface SearchService {

	public List<SearchResponseDto> getProductByNameAndCategory(String productName, String categoryName);
		
}
