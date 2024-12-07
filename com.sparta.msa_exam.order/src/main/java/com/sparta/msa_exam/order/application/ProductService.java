package com.sparta.msa_exam.order.application;

import java.util.List;

import com.sparta.msa_exam.order.application.dtos.ProductResponse;

public interface ProductService {
	List<ProductResponse> getProducts(List<Long> id);
	ProductResponse getProduct(Long productId);
}
