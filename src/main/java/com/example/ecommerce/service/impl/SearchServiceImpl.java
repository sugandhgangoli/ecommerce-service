package com.example.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.SearchResponseDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.APIException;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<SearchResponseDto> getProductByNameAndCategory(String productName, String categoryName)
			throws APIException {

		if (productName == null && categoryName == null)
			throw new APIException("Invalid inputs. Please enter valid inputs");

		List<Product> product = new ArrayList<>();
		Category category = null;
		
			if (categoryName != null)
				category = categoryRepository.findByCategoryName(categoryName);

			if (productName != null && category != null)
				product = this.productRepository.findByProductNameAndCategory(productName, category);
			else if (productName != null && category == null && categoryName == null)
			{
				product = this.productRepository.findByProductName(productName);
				if(product.size() == 0)
					throw new APIException("Invalid inputs. Please enter valid inputs");
			}
			else if (productName == null && category != null)
				product = this.productRepository.findByCategory(category);
			else if (productName == null || category == null)
				throw new APIException("Invalid inputs. Please enter valid inputs");

			this.productRepository.findByProductNameAndCategory(productName, category);
		

		List<SearchResponseDto> searchResponseDto = new ArrayList<>();

		for (Product source : product) {

			SearchResponseDto target = new SearchResponseDto();
			BeanUtils.copyProperties(source, target);
			searchResponseDto.add(target);
		}
		return searchResponseDto;

	}
}
