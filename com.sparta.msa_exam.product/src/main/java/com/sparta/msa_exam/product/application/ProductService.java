package com.sparta.msa_exam.product.application;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sparta.msa_exam.product.application.dtos.CreateProductRequest;
import com.sparta.msa_exam.product.application.dtos.ProductResponse;
import com.sparta.msa_exam.product.common.exception.CustomException;
import com.sparta.msa_exam.product.domain.entity.Product;
import com.sparta.msa_exam.product.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	@CacheEvict(cacheNames = "productsCache", allEntries = true)
	public void createProduct(CreateProductRequest request) {
		validateProductInfo(request.getName());

		productRepository.save(Product.builder()
			.name(request.getName())
			.supplyPrice(request.getSupplyPrice())
			.build());
	}

	@Cacheable(
		cacheNames = "productsCache",
		key = "#pageable.pageNumber + ':' + #pageable.pageSize + ':' + #pageable.sort.toString()"
	)
	public List<ProductResponse> getProducts(Pageable pageable) {
		return productRepository.findAllBy(pageable).stream()
			.map(ProductResponse::from)
			.toList();
	}

	public List<ProductResponse> getProducts(List<Long> ids) {
		return productRepository.findAllById(ids).stream()
			.map(ProductResponse::from)
			.toList();
	}

	private void validateProductInfo(String name) {
		if (productRepository.existsByName(name)) {
			throw new CustomException(HttpStatus.CONFLICT, "이미 존재하는 상품입니다.");
		}
	}
}
