package com.sparta.msa_exam.auth.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.msa_exam.auth.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUsername(String username);
}
