package com.idk.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.idk.demo.exception.ProductNotFoundException;
import com.idk.demo.model.Product;
import com.idk.demo.service.ProductService;

@Controller
@RequestMapping("/admin/products")
//@PreAuthorize("hasRole('ROLE_ADMIN')") // Optional: Secure admin routes
public class ProductUIController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public String listAdminProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "admin-products";
	}

	@GetMapping("/add")
	public String showAddProductForm(Model model) {
		model.addAttribute("product", new Product()); 
		return "add-product";
	}

	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product product, BindingResult result,
			RedirectAttributes redirectAttributes ) {
		if (result.hasErrors()) {
			return "add-product"; 
		}
		productService.save(product);
		redirectAttributes.addFlashAttribute("message", "Product saved successfully!");
		return "redirect:/admin/products";
	}

	@GetMapping("/edit/{id}")
	public String showEditProductForm(@PathVariable("id") Long id, Model model,
			RedirectAttributes redirectAttributes) {
		try {
			Product product = productService.getById(id); 
			model.addAttribute("product", product);
			return "edit-product";
		} catch (ProductNotFoundException e) {
			redirectAttributes.addFlashAttribute("error_message", "Product not found with ID: " + id);
			return "redirect:/admin/products";
		}
	}

	@GetMapping("/view/{id}")
	public String showProductForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Product product = productService.getById(id);
			model.addAttribute("product", product);
			return "view-product";
		} catch (ProductNotFoundException e) {
			redirectAttributes.addFlashAttribute("error_message", "Product not found with ID: " + id);
			return "redirect:/admin/products";
		}
	}

	@PostMapping("/update/{id}")
	public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product product,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {
		if (result.hasErrors()) {
			product.setId(id); 
			return "edit-product";
		}
		product.setId(id);
		productService.save(product);
		redirectAttributes.addFlashAttribute("message", "Product updated successfully!");
		return "redirect:/admin/products";
	}

	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			productService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Product deleted successfully!");
		} catch (ProductNotFoundException e) {
			redirectAttributes.addFlashAttribute("error_message", "Could not delete. Product not found with ID: " + id);
		} catch (DataIntegrityViolationException e) { 
			redirectAttributes.addFlashAttribute("error_message",
					"Could not delete product. It might be referenced elsewhere.");
		}
		return "redirect:/admin/products";
	}
}
