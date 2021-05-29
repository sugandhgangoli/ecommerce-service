package com.example.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.BuyRequestDto;
import com.example.ecommerce.dto.BuyResponseDto;
import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.entity.OrderDetails;
import com.example.ecommerce.entity.Orders;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.APIException;
import com.example.ecommerce.feignclient.BankClient;
import com.example.ecommerce.repository.OrdersRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.BuyService;

@Service
public class BuyServiceImpl implements BuyService {

	@Autowired
	private BankClient bankClient;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private UserRepository userRepository;

	@Value("${constants.accountnumber}")
	private Long defaultAccountNumber;

	@Override
	public BuyResponseDto createOrder(BuyRequestDto buyRequestDto) {
		List<APIException> exceptions = new ArrayList<>();
		List<ProductResponseDto> products = new ArrayList<>();
		List<Product> inventoryProducts = new ArrayList<>();
		
		Orders order = new Orders();
		List<OrderDetails> orderDetails = new ArrayList<>();
		
		double totalPrice = 0d;
		int processedOrder = 0;
		
		for(ProductRequestDto requestedProduct: buyRequestDto.getProducts())
		{
			Product product = this.productRepository.findById(requestedProduct.getProductId());
		
			ProductResponseDto productResponse = new ProductResponseDto();
			Product p = product;
			
			if(p.getProductQuantity() < requestedProduct.getQuantity()) {
				exceptions.add(new APIException("Product "+ p.getProductName() + " is out of stock"));
				
			}
			else {
				p.setProductQuantity(p.getProductQuantity() - requestedProduct.getQuantity());
				inventoryProducts.add(p);
				totalPrice += (p.getProductPrice()*requestedProduct.getQuantity());
				BeanUtils.copyProperties(p, productResponse);
				productResponse.setProductQuantity(requestedProduct.getQuantity());
				productResponse.setProductName(p.getProductName());
				
				products.add(productResponse);
				
				OrderDetails od = new OrderDetails(p.getProductName(), requestedProduct.getQuantity(), requestedProduct.getQuantity()*p.getProductPrice());
				//TODO Call addOrders and addProducts
				
				od.setProduct(p);
				orderDetails.add(od);
				processedOrder++;
				
			}
		}
		
		
		
		//TODO Create and save order
		if(processedOrder == 0)
			throw new APIException("Order Failed" + exceptions);
		else
		{
			
			try
			{
				
				bankClient.transferFund(buyRequestDto.getAccountNumber(), defaultAccountNumber, totalPrice);
				
				for (Product p: inventoryProducts)
				{
					this.productRepository.save(p);
				}
				
				order.setOrderDetails(orderDetails);
				Random random = new Random();
				order.setTrackingNumber(random.nextInt(1000)+1);
				order.setOrderAmount(totalPrice);

				Optional<User> user = this.userRepository.findById(buyRequestDto.getUserId()); 
				order.setUser(user.isPresent() ? user.get() : null);
				
				for (OrderDetails od : order.getOrderDetails())
				{
					od.setOrders(order);
				}
				
				this.ordersRepository.save(order);
			}catch(Exception e) {
				throw new APIException(e.getMessage());
			}
		}
		
		return new BuyResponseDto(order.getOrderId(), products, exceptions);
	}
}
