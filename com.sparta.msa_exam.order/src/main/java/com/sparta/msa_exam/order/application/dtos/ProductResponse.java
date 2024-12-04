package com.sparta.msa_exam.order.application.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse implements Serializable {

	private Long productId;
	private String name;
	private int supplyPrice;
}
