package com.TrueLearn.Cart.usecases.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.TrueLearn.Cart.Util.CartConvert;
import com.TrueLearn.Cart.client.CourseClient;
import com.TrueLearn.Cart.client.UserClient;
import com.TrueLearn.Cart.client.payload.CourseResponse;
import com.TrueLearn.Cart.client.payload.UserResponse;
import com.TrueLearn.Cart.dto.CartRequest;
import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.model.Cart;
import com.TrueLearn.Cart.model.CartStatus;
import com.TrueLearn.Cart.repository.CartRepository;
import com.TrueLearn.Cart.usecases.IAddProductUseCase;



@Service
public class AddProductUseCaseImpl implements IAddProductUseCase{

	@Autowired
	CartRepository cartRepository;
	@Autowired
	private CourseClient courseCliente;
	@Autowired
	private UserClient userClient;
	
	public CartResponse AddProduct(CartRequest cartRequest) throws NotFoundException {
		
		UserResponse userResponse = userClient.getUserByCpf(cartRequest.getUserCpf());
		if(userResponse == null) throw new NotFoundException();
		
		CourseResponse courseResponse = courseCliente.getCourseByCourseId(cartRequest.getCourseId());
		if(courseResponse == null) throw new NotFoundException();
		
		Cart cart = new Cart();
		List<UUID> courseIds = new ArrayList<UUID>();
		if(ObjectUtils.isEmpty(cartRequest.getCartId())) {
			courseIds.add(courseResponse.getCourseId());
			cart.setCartId(UUID.randomUUID().toString());
			cart.setCartStatus(CartStatus.IN_PROGRESS);
			cart.setPurchaseDate(LocalDateTime.now());
			cart.setTotalPrice(courseResponse.getPrice());
			cart.setCoursesIdsList(courseIds);
		}else {
			try {
				cart = cartRepository.findByCartId(cartRequest.getCartId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			cart.setTotalPrice(cart.getTotalPrice().add(courseResponse.getPrice()));
			courseIds = cart.getCoursesIdsList();
			courseIds.add(courseResponse.getCourseId());
			cart.setCoursesIdsList(courseIds);
		}
		
		CartResponse cartResponse = CartConvert.toResponse(cart);
		cartResponse.setUserResponse(userResponse);
		
		
		return cartResponse;
	}
	
	
}
