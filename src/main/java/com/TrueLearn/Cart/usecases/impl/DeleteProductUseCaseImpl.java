package com.TrueLearn.Cart.usecases.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TrueLearn.Cart.Util.CartConvert;
import com.TrueLearn.Cart.client.CourseClient;
import com.TrueLearn.Cart.client.UserClient;
import com.TrueLearn.Cart.client.payload.CourseResponse;
import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.model.Cart;
import com.TrueLearn.Cart.model.CartStatus;
import com.TrueLearn.Cart.repository.CartRepository;
import com.TrueLearn.Cart.usecases.IDeleteProductUseCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DeleteProductUseCaseImpl implements IDeleteProductUseCase {
	
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CourseClient courseCliente;
	@Autowired
	UserClient userClient;
	
	private static final Logger logger = LoggerFactory.getLogger(DeleteProductUseCaseImpl.class);
	
	@Transactional
	public CartResponse deleteProduct(String cartId, UUID courseId) throws Exception {
		logger.info("Course Id value: {}", courseId);
		logger.info("Cart Id value: {}", cartId);
		
		Cart cart = cartRepository.findByCartId(cartId);
		
		if(cart == null) throw new NotFoundException();
		
		if(cart.getCartStatus() == CartStatus.APROVED || cart.getCartStatus() == CartStatus.PENDENT || cart.getCartStatus() == CartStatus.ERROR) {
			logger.info("Cart status value: {}", cart.getCartStatus());
			throw new Exception("The status of the cart is not open to modification!");
		}
		
		List<UUID> courseIds = cart.getCoursesIdsList();
		
		UUID courseToDelete =  courseIds.stream().filter(course -> course.equals(courseId)).findFirst().orElse(null);
		
		if(courseToDelete != null) {
			courseIds.remove(courseToDelete);
			cart.setCoursesIdsList(courseIds);
			cart.setPurchaseDate(LocalDateTime.now());
			CourseResponse courseResponse = courseCliente.getCourseByCourseId(courseToDelete);
			cart.setTotalPrice(cart.getTotalPrice().subtract(courseResponse.getPrice()));
			cartRepository.save(cart);
		}else {
			throw new NotFoundException();
		}
	
		return CartConvert.toResponse(cart);
	}
	
	
	
}
