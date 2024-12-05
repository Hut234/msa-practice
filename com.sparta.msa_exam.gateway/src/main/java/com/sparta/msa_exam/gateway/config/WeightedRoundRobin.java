package com.sparta.msa_exam.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sparta.msa_exam.gateway.filter.JwtAuthenticationFilter;

@Configuration
public class WeightedRoundRobin {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder, JwtAuthenticationFilter jwtAuthenticationFilter) {
		return builder.routes()
			.route(r -> r.path("/products/**")
				.and().weight("/product", 7)
				.filters(f -> f.filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter())))
				.uri("http://localhost:19093"))

			.route(r -> r.path("/products/**")
				.and().weight("/product", 3)
				.filters(f -> f.filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter())))
				.uri("http://localhost:19094"))
			.build();
	}
}
