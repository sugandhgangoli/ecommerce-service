package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	
	//public List<Product> findByProductNameOrCategory(SearchRequestDto searchRequestDto);
	
	//@Query("from Product where product.productName = :productName or product.")
	//public List<Product> findByProductNameOrCategory(String productName, Category category);

	public List<Product> findByProductName(String productName);
	
	public List<Product> findByCategory(Category category);

	public List<Product> findByProductNameAndCategory(String productName, Category category);
	
	@Query("from Product where productId =:productId")
	public Product findById(int productId);

}
