package com.sparta.msa_exam.order.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sparta.msa_exam.order.application.dtos.ProductResponse;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

	@GetMapping("/products/ids")
	List<ProductResponse> getProducts(@RequestParam List<Long> id);

	@GetMapping("/products/{productId}")
	ProductResponse getProduct(@PathVariable Long productId);
}
