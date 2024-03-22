package com.TrueLearn.Cart.usecases;

import com.TrueLearn.Cart.dto.CartRequest;
import com.TrueLearn.Cart.dto.CartResponse;

public interface IUpdateCartUseCase {
	
	CartResponse updateCart(CartRequest cartRequest);

}
