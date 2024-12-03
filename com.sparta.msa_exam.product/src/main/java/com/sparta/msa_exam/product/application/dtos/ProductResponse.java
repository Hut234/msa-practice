package com.sparta.msa_exam.product.application.dtos;

import java.io.Serializable;

import com.sparta.msa_exam.product.domain.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse implements Serializable {

	private Long productId;
	private String name;
	private int supplyPrice;

	public static ProductResponse from(Product product) {
		return new ProductResponse(
			product.getId(),
			product.getName(),
			product.getSupplyPrice());
	}
}
