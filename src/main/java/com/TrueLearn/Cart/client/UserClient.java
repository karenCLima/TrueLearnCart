package com.TrueLearn.Cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.TrueLearn.Cart.client.payload.UserResponse;

@FeignClient(name = "UserClient", url = "http://localhost:8081/user")
public interface UserClient {
	
	@GetMapping( "/cpf/{cpf}")
	UserResponse getUserByCpf(@PathVariable String cpf);

}
