package com.idk.demo.exception;

public class ProductNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Long id) {
		super("Product not found , Id = " + id.intValue());
	}

}
