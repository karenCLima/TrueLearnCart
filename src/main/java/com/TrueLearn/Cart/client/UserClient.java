package com.TrueLearn.Cart.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.TrueLearn.Cart.client.payload.UserResponse;

public interface UserClient {
	
	@GetExchange( value = "/cpf/{cpf}")
	UserResponse getUserByCpf(@PathVariable String cpf);

}
