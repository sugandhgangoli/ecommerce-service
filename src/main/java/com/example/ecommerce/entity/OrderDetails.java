package com.example.ecommerce.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "order_details")
public class OrderDetails {
	
	

	public OrderDetails(String productDetails, int quantity, double price) {
		super();
		this.productDetails = productDetails;
		this.quantity = quantity;
		this.price = price;
	}


	public OrderDetails() {
		super();
	}


	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int detailId;
	
	@NotNull
	private String productDetails;
	
	@NotNull
	private int quantity;
	
	@NotNull
	private double price;
	
	@ManyToOne(targetEntity = Product.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne(targetEntity = Orders.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "orders_id")
	private Orders orders;


	public int getDetailId() {
		return detailId;
	}


	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}


	public String getProductDetails() {
		return productDetails;
	}


	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Orders getOrders() {
		return orders;
	}


	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
	/*
	 * public void addProducts(Product product) { if(this.product == null)
	 * this.product = new ArrayList<>(); this.product.add(product); }
	 */
}
