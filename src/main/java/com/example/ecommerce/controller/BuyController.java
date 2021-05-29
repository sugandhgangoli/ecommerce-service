package com.example.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.BuyRequestDto;
import com.example.ecommerce.dto.BuyResponseDto;
import com.example.ecommerce.dto.ResponseObject;
import com.example.ecommerce.dto.SearchResponseDto;
import com.example.ecommerce.exception.APIException;
import com.example.ecommerce.service.BuyService;

@RestController
@RequestMapping("/orders")
public class BuyController {

	private static final Logger logger = LoggerFactory.getLogger(BuyController.class);
	
	@Autowired
	private BuyService buyService;

	@PostMapping("/place-order")
	public ResponseEntity<ResponseObject> createOrder(@Validated @RequestBody BuyRequestDto buyRequestDto) throws APIException {
		logger.info("In method : createOrder");	
		BuyResponseDto buyResoponseDto = this.buyService.createOrder(buyRequestDto);
		logger.info("Out method : createOrder");
		return new ResponseEntity<ResponseObject>(new ResponseObject("Order Successfull.", buyResoponseDto), HttpStatus.ACCEPTED);
	}
}
