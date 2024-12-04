package com.sparta.msa_exam.order.application.dtos;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateOrderRequest {

	@NotNull
	private Long userId;
	@NotNull
	private List<Long> productIds;
}
