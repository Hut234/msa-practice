package com.sparta.msa_exam.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.msa_exam.product.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	boolean existsByName(String name);
}
