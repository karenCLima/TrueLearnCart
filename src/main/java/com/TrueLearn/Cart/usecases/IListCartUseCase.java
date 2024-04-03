package com.TrueLearn.Cart.usecases;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;

import com.TrueLearn.Cart.dto.CartResponse;

public interface IListCartUseCase {
	
	Page<CartResponse> getAllCarts(int page, int size, String direction);
	
	CartResponse getCartByCartId(String cardId) throws NotFoundException;

}
