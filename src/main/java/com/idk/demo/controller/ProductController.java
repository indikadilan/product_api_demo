package com.idk.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idk.demo.model.Product;
import com.idk.demo.service.ProductService;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productRegisterService;

	@PostMapping("products")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Product save(@RequestBody Product product) {
		return productRegisterService.save(product);
	}

	@GetMapping("products")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<Product> getProducts() {
		return productRegisterService.getAllProducts();
	}

	@GetMapping("products/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Product getProductById(@PathVariable("id") Long id) {
		return productRegisterService.getById(id);
	}

	@PutMapping("products/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Product update(@PathVariable("id") Long id, @RequestBody Product product) {
		return productRegisterService.update(id, product);
	}

	@DeleteMapping("products/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String delete(@PathVariable("id") Long id) {
		return productRegisterService.delete(id);
	}

}
