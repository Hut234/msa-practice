package com.sparta.msa_exam.gateway.filter;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory {

	private static final String AUTHENTICATION_SCHEME = "Bearer ";

	@Value("${jwt.secret}")
	private String secret;

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

			if (isNotValid(authorizationHeader)) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}

			SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
			String token = authorizationHeader.replace(AUTHENTICATION_SCHEME, "");
			if (isNotValid(key, token)) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}

			return chain.filter(exchange);
		};
	}

	private boolean isNotValid(String authorizationHeader) {
		return !StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith(AUTHENTICATION_SCHEME);
	}

	private boolean isNotValid(SecretKey key, String jwt) {
		boolean result = true;

		try {
			Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt);
		} catch (Exception e) {
			result = false;
		}

		return result;
	}
}
