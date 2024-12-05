package com.sparta.msa_exam.auth.libs.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenProvider {

	private static final String AUTHORIZATION_TYPE = "Bearer ";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	private Key key;

	@PostConstruct
	private void init() {
		key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
	}

	public String createToken(String username) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + expiration);

		return AUTHORIZATION_TYPE + Jwts.builder()
			.subject(username)
			.issuedAt(now)
			.expiration(expiry)
			.signWith(key)
			.compact();
	}
}
