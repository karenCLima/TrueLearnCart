package com.TrueLearn.Cart.usecases.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.TrueLearn.Cart.model.*;
import com.TrueLearn.Cart.Util.CartConvert;
import com.TrueLearn.Cart.client.CourseClient;
import com.TrueLearn.Cart.client.UserClient;
import com.TrueLearn.Cart.client.payload.CourseResponse;
import com.TrueLearn.Cart.client.payload.UserResponse;
import com.TrueLearn.Cart.dto.CartRequest;
import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.repository.CartRepository;
import com.TrueLearn.Cart.usecases.IUpdateCartUseCase;

public class UpdateCartUseCaseImpl implements IUpdateCartUseCase {
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	private CourseClient courseCliente;
	
	@Autowired
	private UserClient userClient;


	@Override
	public CartResponse updateCart(CartRequest cartRequest) throws NotFoundException {
		Cart cart = cartRepository.findByCartId(cartRequest.getCartId());
		CartResponse cartResponse = CartConvert.toResponse(cart);
		
		CourseResponse courseResponse = courseCliente.getCourseByCourseId(cartRequest.getCourseId());
		if(courseResponse == null) throw new NotFoundException();
		
		UserResponse userResponse = userClient.getUserByCpf(cartRequest.getUserCpf());
		if(userResponse == null) throw new NotFoundException();
		
		return null;
	}

}
