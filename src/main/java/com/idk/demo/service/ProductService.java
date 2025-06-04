package com.idk.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idk.demo.exception.ProductNotFoundException;
import com.idk.demo.model.Product;
import com.idk.demo.repository.ProductRegisterRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRegisterRepository productRegisterRepository;

	public Product save(Product product) {
		product.setCreate_at(LocalDateTime.now());
		return productRegisterRepository.save(product);
	}

	public List<Product> getAllProducts() {
		return productRegisterRepository.findAll();
	}

	public Product getById(Long id) {
		return productRegisterRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

	}

	public Product update(Long id, Product product) {
		return productRegisterRepository.findById(id).map(existProduct -> {
			existProduct.setName(product.getName());
			existProduct.setDescription(product.getDescription());
			existProduct.setImageUrl(product.getImageUrl());
			existProduct.setPrice(product.getPrice());
			existProduct.setAvailable_quantity(product.getAvailable_quantity());
			productRegisterRepository.save(existProduct);
			return existProduct;
		}).orElseThrow(() -> new ProductNotFoundException(id));
	}

	public String delete(Long id) {
		if (!productRegisterRepository.existsById(id)) {
			throw new ProductNotFoundException(id);
		}
		productRegisterRepository.deleteById(id);
		return "Product id = " + id + " is deleted";
	}

}
