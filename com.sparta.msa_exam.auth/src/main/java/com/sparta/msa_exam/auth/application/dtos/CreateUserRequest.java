package com.sparta.msa_exam.auth.application.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserRequest {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
