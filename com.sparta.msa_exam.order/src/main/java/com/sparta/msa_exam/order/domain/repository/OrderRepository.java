package com.sparta.msa_exam.order.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sparta.msa_exam.order.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT o FROM Order o JOIN FETCH o.orderProducts WHERE o.id = :id")
	Optional<Order> findByIdWithOrderProducts(Long id);
}
