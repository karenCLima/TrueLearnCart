package com.TrueLearn.Cart.usecases.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.TrueLearn.Cart.Util.CartConvert;
import com.TrueLearn.Cart.client.UserClient;
import com.TrueLearn.Cart.client.payload.UserResponse;
import com.TrueLearn.Cart.dto.CartRequest;
import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.model.Cart;
import com.TrueLearn.Cart.model.CartStatus;
import com.TrueLearn.Cart.repository.CartRepository;
import com.TrueLearn.Cart.usecases.ICreateCartUseCase;

@Service
public class CreateCartUseCaseImpl implements ICreateCartUseCase{
	
	@Autowired
	CartRepository cartRepository;
	@Autowired
	private UserClient userClient;
	
	private static final Logger logger = LoggerFactory.getLogger(CreateCartUseCaseImpl.class);

	@Override
	public CartResponse createCart(String userCpf) throws NotFoundException {
		
		logger.info("CPF value: {}", userCpf);
		
		UserResponse userResponse = userClient.getUserByCpf(userCpf);
		System.out.println(userResponse.getCpf());
		if(userResponse == null) throw new NotFoundException();
		
		Cart cart = new Cart();
		List<UUID> courseIds = new ArrayList<UUID>();
		cart.setCartId(UUID.randomUUID().toString());
		cart.setCartStatus(CartStatus.CREATED);
		cart.setPurchaseDate(LocalDateTime.now());
		cart.setCoursesIdsList(courseIds);
		
		CartResponse cartResponse = CartConvert.toResponse(cartRepository.save(cart));
		cartResponse.setUserResponse(userResponse);
		
		return cartResponse;
	}

}
