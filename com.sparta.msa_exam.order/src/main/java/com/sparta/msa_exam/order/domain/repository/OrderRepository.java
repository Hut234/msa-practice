package com.sparta.msa_exam.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.msa_exam.order.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
