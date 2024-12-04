package com.sparta.msa_exam.order.application.dtos;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateOrderRequest {

	@Positive
	private long productId;
}
