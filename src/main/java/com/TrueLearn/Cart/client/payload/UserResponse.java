package com.TrueLearn.Cart.client.payload;

import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	
	private UUID userId;
	
	private String name;
	
	private String cpf;
	
	private String email;
	
}
