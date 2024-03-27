package com.TrueLearn.Cart.usecases;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.TrueLearn.Cart.model.Cart;
import com.TrueLearn.Cart.dto.CartResponse;

public interface IUpdateCartUseCase {
	
	CartResponse updateCart(Cart cart) throws NotFoundException;

}
