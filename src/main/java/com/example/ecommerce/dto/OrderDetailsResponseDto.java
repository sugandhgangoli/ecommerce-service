package com.example.ecommerce.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailsResponseDto {

	private int orderId;
	private Date orderDate;
	private List<ProductResponseDto> products;
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public void addProducts(ProductResponseDto product)
	{
		if(this.products == null)
			this.products = new ArrayList<>();
		this.products.add(product);
	}
	
	
	
}
