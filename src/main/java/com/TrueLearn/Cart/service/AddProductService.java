package com.TrueLearn.Cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import com.TrueLearn.Cart.model.Cart;
import com.TrueLearn.Cart.model.CartStatus;
import com.TrueLearn.Cart.Util.CartConvert;
import com.TrueLearn.Cart.client.CourseClient;
import com.TrueLearn.Cart.client.UserClient;
import com.TrueLearn.Cart.client.payload.CourseResponse;
import com.TrueLearn.Cart.client.payload.UserResponse;
import com.TrueLearn.Cart.dto.CartRequest;
import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.repository.CartRepository;

@Service
public class AddProductService {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	CourseClient courseCliente;
	@Autowired
	UserClient userClient;
	
	public CartResponse AddProduct(CartRequest cartRequest) throws NotFoundException {
		
		UserResponse userResponse = userClient.getUserByCpf(cartRequest.getUserCpf());
		if(userResponse == null) throw new NotFoundException();
		
		CourseResponse courseResponse = courseCliente.getCourseByCourseId(cartRequest.getCourseId());
		if(courseResponse == null) throw new NotFoundException();
		
		Cart cart = new Cart();
		List<CourseResponse> courseResponses = new ArrayList<CourseResponse>();
		if(ObjectUtils.isEmpty(cartRequest.getCartId())) {
			courseResponses.add(courseResponse);
			cart.setCartId(UUID.randomUUID().toString());
			cart.setCartStatus(CartStatus.IN_PROGRESS);
			cart.setPurchaseDate(LocalDateTime.now());
			cart.setTotalPrice(courseResponse.getPrice());
			cart.setCourseResponsesList(courseResponses);
		}else {
			cart = cartRepository.findByCartId(cartRequest.getCartId()).orElseThrow( () -> new NotFoundException());
			cart.setTotalPrice(cart.getTotalPrice().add(courseResponse.getPrice()));
			courseResponses = cart.getCourseResponsesList();
			courseResponses.add(courseResponse);
			cart.setCourseResponsesList(courseResponses);
		}
		
		CartResponse cartResponse = CartConvert.toResponse(cart);
		cartResponse.setUserResponse(userResponse);
		
		
		return cartResponse;
	}
	
	
}
