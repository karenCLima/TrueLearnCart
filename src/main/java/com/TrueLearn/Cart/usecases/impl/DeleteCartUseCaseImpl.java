package com.TrueLearn.Cart.usecases.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.TrueLearn.Cart.model.Cart;
import com.TrueLearn.Cart.repository.CartRepository;
import com.TrueLearn.Cart.usecases.IDeleteCartUseCase;

public class DeleteCartUseCaseImpl implements IDeleteCartUseCase{
	
	@Autowired
	CartRepository cartRepository;

	@Override
	public void deleteCart(String cartId) throws NotFoundException {
		Cart cart = cartRepository.findByCartId(cartId);
		
		if(cart == null) throw new NotFoundException();
		
		cartRepository.delete(cart);
	}

}
