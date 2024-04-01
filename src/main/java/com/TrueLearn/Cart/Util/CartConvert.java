package com.TrueLearn.Cart.Util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

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
	
	public static List<CartResponse> toResponseList(List<Cart> carts){
		List<CartResponse> cartResponses = new ArrayList<>();
		for(Cart cart: carts) {
			CartResponse cartResponse = CartConvert.toResponse(cart);
			cartResponses.add(cartResponse);
		}
		return cartResponses;
	}
	
	public static Page<CartResponse> toResponsePage(Page<Cart> carts) {
		List<CartResponse> cartResponses = new ArrayList<>();
		for(Cart cart : carts) {
			CartResponse cartResponse = CartConvert.toResponse(cart);
			cartResponses.add(cartResponse);
		}
		return new PageImpl<>(cartResponses);
		
	}

}
