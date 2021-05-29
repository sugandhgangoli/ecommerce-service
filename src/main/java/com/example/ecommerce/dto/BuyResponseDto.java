package com.example.ecommerce.dto;

import java.util.List;

import com.example.ecommerce.exception.APIException;

public class BuyResponseDto {

	private int orderId;
	private List<ProductResponseDto> products;
	private List<APIException> failedProducts;
	private double price;
	
	
	public BuyResponseDto(int orderId, List<ProductResponseDto> products, List<APIException> failedProducts) {
		super();
		this.orderId = orderId;
		this.products = products;
		this.failedProducts = failedProducts;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<ProductResponseDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductResponseDto> products) {
		this.products = products;
	}
	
	public List<APIException> getFailedProducts() {
		return failedProducts;
	}
	public void setFailedProducts(List<APIException> failedProducts) {
		this.failedProducts = failedProducts;
	}
	public double getPrice() {
		for(ProductResponseDto product: products)
		{
			price += product.getTotalCost();
		}
		return price;
	}
	
	
}
