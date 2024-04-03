package com.TrueLearn.Cart.usecases.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.TrueLearn.Cart.Util.CartConvert;
import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.model.Cart;
import com.TrueLearn.Cart.repository.CartRepository;
import com.TrueLearn.Cart.usecases.IListCartUseCase;

@Service
public class ListCartUseCaseImpl implements IListCartUseCase{
	
	@Autowired
	CartRepository cartRepository;

	@Override
	public Page<CartResponse> getAllCarts(int page, int size, String direction) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction.toUpperCase()), "cartId");
		Page<Cart>cart = cartRepository.findAll(pageRequest);
		return CartConvert.toResponsePage(cart);
	}

	@Override
	public CartResponse getCartByCartId(String cardId) throws NotFoundException {
		Cart existCart = cartRepository.findByCartId(cardId);
		if(existCart== null) throw new NotFoundException();
		return CartConvert.toResponse(existCart);
	}

}
