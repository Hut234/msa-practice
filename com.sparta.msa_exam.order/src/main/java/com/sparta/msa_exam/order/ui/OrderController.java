package com.sparta.msa_exam.order.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.msa_exam.order.application.OrderProductService;
import com.sparta.msa_exam.order.application.OrderService;
import com.sparta.msa_exam.order.application.dtos.CreateOrderRequest;
import com.sparta.msa_exam.order.application.dtos.OrderResponse;
import com.sparta.msa_exam.order.application.dtos.UpdateOrderRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final OrderProductService orderProductService;

	@PostMapping("/orders")
	public ResponseEntity<Void> createOrder(@Valid @RequestBody CreateOrderRequest request, @RequestParam(required = false) boolean fail) {
		orderProductService.createOrderProduct(request, fail);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/orders/{orderId}")
	public ResponseEntity<OrderResponse> getOrder(@PathVariable long orderId) {
		return ResponseEntity.ok(orderService.getOrder(orderId));
	}

	@PutMapping("/orders/{orderId}")
	public ResponseEntity<Void> updateOrder(@PathVariable Long orderId, @Valid @RequestBody UpdateOrderRequest request) {
		orderProductService.addOrderProduct(orderId, request);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
