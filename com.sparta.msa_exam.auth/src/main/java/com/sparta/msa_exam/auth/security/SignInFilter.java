package com.sparta.msa_exam.auth.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignInFilter extends UsernamePasswordAuthenticationFilter {

	@Value("${server.port}")
	private String port;
	private final JwtTokenProvider jwtTokenProvider;

	public SignInFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
		setFilterProcessesUrl("/auth/sign-in");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			SignInRequest signinRequest = new ObjectMapper().readValue(request.getInputStream(), SignInRequest.class);
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
		UserDetailsImpl userDetails = (UserDetailsImpl)authResult.getPrincipal();
		String token = jwtTokenProvider.createToken(userDetails.getUsername());
		response.addHeader(HttpHeaders.AUTHORIZATION, token);
		response.addHeader("Server-Port", port);
	}
}
