package com.sparta.msa_exam.auth.application;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sparta.msa_exam.auth.application.dtos.CreateUserRequest;
import com.sparta.msa_exam.auth.libs.common.exception.CustomException;
import com.sparta.msa_exam.auth.domain.entity.User;
import com.sparta.msa_exam.auth.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public void createUser(CreateUserRequest request) {
		validateUserInfo(request.getUsername());

		userRepository.save(User.builder()
			.username(request.getUsername())
			.password(passwordEncoder.encode(request.getPassword()))
			.build());
	}

	private void validateUserInfo(String username) {
		if (userRepository.existsByUsername(username)) {
			throw new CustomException(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다.");
		}
	}
}
