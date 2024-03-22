package com.TrueLearn.Cart.Util;

import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.model.Cart;

public class CartConvert {
	
	public static CartResponse toResponse(Cart cart) {
		CartResponse cartResponse = new CartResponse();
		
		cartResponse.setCartId(cart.getCartId());
		cartResponse.setCartStatus(cart.getCartStatus());
		cartResponse.setPurchaseDate(cart.getPurchaseDate());
		cartResponse.setTotalPrice(cart.getTotalPrice());
		cartResponse.setCourseIds(cart.getCoursesIdsList());
		return cartResponse;
	} 

}
