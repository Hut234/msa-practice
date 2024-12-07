package com.sparta.msa_exam.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.msa_exam.order.domain.entity.OrderProduct;

public interface OrderProductListRepository extends JpaRepository<OrderProduct, Long> {
}
