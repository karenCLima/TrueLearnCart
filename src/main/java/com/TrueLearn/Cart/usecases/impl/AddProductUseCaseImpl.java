package com.TrueLearn.Cart.usecases.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.TrueLearn.Cart.Util.CartConvert;
import com.TrueLearn.Cart.client.CourseClient;
import com.TrueLearn.Cart.client.payload.CourseResponse;
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
	
	private static final Logger logger = LoggerFactory.getLogger(AddProductUseCaseImpl.class);
	
	public CartResponse AddProduct(String cartId, UUID courseId) throws NotFoundException {
		
		logger.info("Course Id value: {}", courseId);
		
		Cart cart = cartRepository.findByCartId(cartId);
		if(cart == null) throw new NotFoundException();
		
		CourseResponse courseResponse = courseCliente.getCourseByCourseId(courseId);
		if(courseResponse == null) throw new NotFoundException();
		
		if(cart.getCartStatus()!= CartStatus.CREATED || cart.getCartStatus() != CartStatus.IN_PROGRESS) {
			throw new NotFoundException();
		}
		
		List<UUID> courseIds = cart.getCoursesIdsList();
		cart.setTotalPrice(cart.getTotalPrice().add(courseResponse.getPrice()));
		courseIds.add(courseResponse.getCourseId());
		cart.setCoursesIdsList(courseIds);
		cart.setCartStatus(CartStatus.IN_PROGRESS);
		cart.setPurchaseDate(LocalDateTime.now());
		
		CartResponse cartResponse = CartConvert.toResponse(cartRepository.save(cart));
		
		
		return cartResponse;
	}


	
}
