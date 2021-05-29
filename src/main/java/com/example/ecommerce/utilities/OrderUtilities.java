package com.example.ecommerce.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;

public class OrderUtilities {

	@Autowired
	private ProductRepository productRepository;
	
	/*
	 * public static double countTotalPrice() { double total = 0.0; List<Product>
	 * productList = new ArrayList<>(); for(Product product : productList) {
	 * product.getProductId(); product.getProductPrice();
	 * 
	 * } }
	 */
}
