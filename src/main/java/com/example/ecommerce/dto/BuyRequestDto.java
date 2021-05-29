package com.example.ecommerce.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class BuyRequestDto {

	@NotNull(message = "Please provide userId")
	private int userId;
	
	@NotNull(message = "Please provide account number")
	private long accountNumber;
	
	private List<ProductRequestDto> products;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public List<ProductRequestDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductRequestDto> products) {
		this.products = products;
	}
	
}
