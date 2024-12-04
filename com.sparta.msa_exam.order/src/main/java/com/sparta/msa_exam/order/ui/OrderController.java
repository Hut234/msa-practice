package com.sparta.msa_exam.order.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.msa_exam.order.application.OrderProductService;
import com.sparta.msa_exam.order.application.dtos.CreateOrderRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderProductService orderProductService;

	@PostMapping("/orders")
	public ResponseEntity<Void> createOrder(@Valid @RequestBody CreateOrderRequest request, @RequestParam(required = false) boolean fail) {
		orderProductService.createOrderProduct(request, fail);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
