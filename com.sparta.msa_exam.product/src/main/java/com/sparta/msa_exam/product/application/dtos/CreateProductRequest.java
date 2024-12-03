package com.sparta.msa_exam.product.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateProductRequest {

	@NotBlank
	private String name;
	@Positive
	private int supplyPrice;
}
