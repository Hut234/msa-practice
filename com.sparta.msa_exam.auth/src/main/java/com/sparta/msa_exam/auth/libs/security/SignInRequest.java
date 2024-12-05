package com.sparta.msa_exam.auth.libs.security;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
