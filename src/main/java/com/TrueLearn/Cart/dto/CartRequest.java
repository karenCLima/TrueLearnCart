package com.TrueLearn.Cart.dto;

import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CartRequest {
	
	private String cartId;
	
	private UUID courseId;
	
	private String userCpf;

}
