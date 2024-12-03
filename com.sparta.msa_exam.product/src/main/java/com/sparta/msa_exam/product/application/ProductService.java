package com.sparta.msa_exam.product.application;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sparta.msa_exam.product.application.dtos.CreateProductRequest;
import com.sparta.msa_exam.product.common.exception.CustomException;
import com.sparta.msa_exam.product.domain.entity.Product;
import com.sparta.msa_exam.product.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public void createProduct(CreateProductRequest request) {
		validateProductInfo(request.getName());

		productRepository.save(Product.builder()
			.name(request.getName())
			.supplyPrice(request.getSupplyPrice())
			.build());
	}

	private void validateProductInfo(String name) {
		if (productRepository.existsByName(name)) {
			throw new CustomException(HttpStatus.CONFLICT, "이미 존재하는 상품입니다.");
		}
	}
}
