package com.TrueLearn.Cart.usecases;

import java.util.List;

import com.TrueLearn.Cart.dto.CartResponse;

public interface IListCartUseCase {
	
	List<CartResponse> getAllCarts();
	
	CartResponse getCartByCartId(String cardId);

}
