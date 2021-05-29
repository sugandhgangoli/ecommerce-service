package com.example.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductRequestDto {
	
	@NotNull(message = "Please provide productId")
	@NotBlank
	private int productId;
	
	@NotNull(message = "Please provide quantity to be purchased")
	@NotBlank(message = "Please provide quantity to be purchased")
	private int quantity;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
