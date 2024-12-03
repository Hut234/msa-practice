package com.sparta.msa_exam.product.domain.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.msa_exam.product.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	boolean existsByName(String name);
	List<Product> findAllBy(Pageable pageable);
}
