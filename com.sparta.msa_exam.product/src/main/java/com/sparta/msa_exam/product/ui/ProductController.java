package com.sparta.msa_exam.product.ui;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.msa_exam.product.application.ProductService;
import com.sparta.msa_exam.product.application.dtos.CreateProductRequest;
import com.sparta.msa_exam.product.application.dtos.ProductResponse;

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

	@GetMapping("/products")
	public ResponseEntity<List<ProductResponse>> getProducts(Pageable pageable) {
		return ResponseEntity.ok(productService.getProducts(pageable));
	}

	@GetMapping("/products/ids")
	public ResponseEntity<List<ProductResponse>> getProducts(@RequestParam List<Long> id) {
		return ResponseEntity.ok(productService.getProducts(id));
	}
}
