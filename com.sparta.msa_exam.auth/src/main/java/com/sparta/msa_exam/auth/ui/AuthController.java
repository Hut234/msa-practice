package com.sparta.msa_exam.auth.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.msa_exam.auth.application.AuthService;
import com.sparta.msa_exam.auth.application.dtos.CreateUserRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/auth/sign-up")
	public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequest request) {
		authService.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
