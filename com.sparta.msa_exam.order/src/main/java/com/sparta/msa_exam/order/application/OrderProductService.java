package com.sparta.msa_exam.order.application;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sparta.msa_exam.order.application.dtos.CreateOrderRequest;
import com.sparta.msa_exam.order.application.dtos.ProductResponse;
import com.sparta.msa_exam.order.application.dtos.UpdateOrderRequest;
import com.sparta.msa_exam.order.ui.infra.client.ProductServiceClient;
import com.sparta.msa_exam.order.libs.common.exception.CustomException;
import com.sparta.msa_exam.order.domain.entity.Order;
import com.sparta.msa_exam.order.domain.entity.OrderProduct;
import com.sparta.msa_exam.order.domain.repository.OrderProductRepository;
import com.sparta.msa_exam.order.domain.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProductService {

	private final ProductServiceClient productServiceClient;
	private final OrderRepository orderRepository;
	private final OrderProductRepository orderProductRepository;

	@CircuitBreaker(name = "OrderProductService", fallbackMethod = "fallbackCreateOrderProduct")
	public void createOrderProduct(CreateOrderRequest request, boolean fail) {
		if (fail) throw new RuntimeException("fail test case");

		List<ProductResponse> products = productServiceClient.getProducts(request.getProductIds());
		List<OrderProduct> orderProducts = products.stream()
			.map(product -> OrderProduct.builder()
				.productId(product.getProductId())
				.price(product.getSupplyPrice())
				.build())
			.toList();

		Order order = orderRepository.save(new Order(request.getUserId()));

		orderProducts.forEach(orderProduct -> orderProduct.addOrder(order));
		orderProductRepository.saveAll(orderProducts);
	}

	@CacheEvict(cacheNames = "orderCache", key = "args[0]")
	public void addOrderProduct(Long orderId, UpdateOrderRequest request) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "존재하지 않는 주문입니다."));

		ProductResponse product = productServiceClient.getProduct(request.getProductId());

		OrderProduct orderProduct = OrderProduct.builder()
			.productId(product.getProductId())
			.price(product.getSupplyPrice())
			.build();

		orderProduct.addOrder(order);
		orderProductRepository.save(orderProduct);
	}

	private void fallbackCreateOrderProduct(Exception e) {
		log.error("잠시 후에 주문 추가를 요청해주세요.");
	}
}
