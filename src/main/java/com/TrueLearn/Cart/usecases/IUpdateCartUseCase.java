package com.TrueLearn.Cart.usecases;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.TrueLearn.Cart.dto.CartRequest;
import com.TrueLearn.Cart.dto.CartResponse;

public interface IUpdateCartUseCase {
	
	CartResponse updateCart(CartRequest cartRequest) throws NotFoundException;

}
