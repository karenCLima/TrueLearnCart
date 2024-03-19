package com.TrueLearn.Cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.TrueLearn.Cart.client.payload.UserResponse;

@FeignClient(name = "UserClient", url = "http://loclahost:8081/user")
public interface UserClient {
	
	@RequestMapping(method = RequestMethod.GET ,value = "/cpf/{cpf}")
	UserResponse getUserByCpf(@PathVariable String cpf);

}
