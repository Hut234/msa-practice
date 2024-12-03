package com.sparta.msa_exam.product.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.msa_exam.product.application.ProductService;
import com.sparta.msa_exam.product.application.dtos.CreateProductRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping("/products")
	public ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductRequest request) {
		productService.createProduct(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
