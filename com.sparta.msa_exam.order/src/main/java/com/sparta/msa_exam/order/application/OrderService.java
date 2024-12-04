package com.sparta.msa_exam.order.application;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sparta.msa_exam.order.application.dtos.OrderResponse;
import com.sparta.msa_exam.order.common.exception.CustomException;
import com.sparta.msa_exam.order.domain.entity.Order;
import com.sparta.msa_exam.order.domain.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	@Cacheable(cacheNames = "orderCache", key = "args[0]")
	public OrderResponse getOrder(long orderId) {
		Order order = orderRepository.findByIdWithOrderProducts(orderId)
			.orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "존재하지 않는 주문입니다."));

		return OrderResponse.from(order);
	}
}
