package com.TrueLearn.Cart.usecases.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.repository.CartRepository;
import com.TrueLearn.Cart.usecases.IListCartUseCase;

public class ListCartUseCaseImpl implements IListCartUseCase{
	
	@Autowired
	CartRepository cartRepository;

	@Override
	public List<CartResponse> getAllCarts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartResponse getCartByCartId(String cardId) {
		// TODO Auto-generated method stub
		return null;
	}

}
