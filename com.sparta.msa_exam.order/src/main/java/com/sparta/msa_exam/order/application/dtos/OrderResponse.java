package com.sparta.msa_exam.order.application.dtos;

import java.io.Serializable;
import java.util.List;

import com.sparta.msa_exam.order.domain.entity.Order;
import com.sparta.msa_exam.order.domain.entity.OrderProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponse implements Serializable {

	private Long orderId;
	private List<ProductInfo> productInfoList;

	public static OrderResponse from(Order order) {
		return new OrderResponse(
			order.getId(),
			order.getOrderProducts().stream()
				.map(ProductInfo::from)
				.toList());
	}

	@Getter
	@AllArgsConstructor
	private static class ProductInfo implements Serializable {
		private Long productId;
		private int price;

		public static ProductInfo from(OrderProduct orderProduct) {
			return new ProductInfo(orderProduct.getProductId(), orderProduct.getPrice());
		}
	}
}
