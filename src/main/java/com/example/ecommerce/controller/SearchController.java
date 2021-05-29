package com.example.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.ResponseObject;
import com.example.ecommerce.dto.SearchResponseDto;
import com.example.ecommerce.exception.APIException;
import com.example.ecommerce.service.SearchService;

@RestController
@RequestMapping("/products")
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SearchService searchService;

	@GetMapping("/search")
	public ResponseEntity<ResponseObject> getProductByNameOrCategory(
			@Validated @RequestParam(required = false) String productName,
			@RequestParam(required = false) String categoryName) throws APIException {
		logger.info("In method : getProductByNameOrCategory");
		List<SearchResponseDto> searchResoponseDto = this.searchService.getProductByNameAndCategory(productName,
				categoryName);
		logger.info("Out method : getProductByNameOrCategory");
		return new ResponseEntity<ResponseObject>(new ResponseObject("Product is available.", searchResoponseDto),
				HttpStatus.ACCEPTED);
		
	}

}
