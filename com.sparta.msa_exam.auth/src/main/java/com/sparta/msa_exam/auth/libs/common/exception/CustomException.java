package com.sparta.msa_exam.auth.libs.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
	private HttpStatus status;
	private String message;
}
