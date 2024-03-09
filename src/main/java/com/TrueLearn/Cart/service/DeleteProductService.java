package com.TrueLearn.Cart.service;

import java.awt.Cursor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TrueLearn.Cart.Util.CartConvert;
import com.TrueLearn.Cart.client.CourseClient;
import com.TrueLearn.Cart.client.UserClient;
import com.TrueLearn.Cart.client.payload.CourseResponse;
import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.model.Cart;
import com.TrueLearn.Cart.model.CartStatus;
import com.TrueLearn.Cart.repository.CartRepository;

@Service
public class DeleteProductService {
	
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CourseClient courseCliente;
	@Autowired
	UserClient userClient;
	
	public CartResponse deleteProduct(String cartId, UUID courseId) throws Exception {
		
		Cart cart = cartRepository.findByCartId(cartId);
		
		if(cart == null) throw new NotFoundException();
		
		if(cart.getCartStatus() == CartStatus.APROVED || cart.getCartStatus() == CartStatus.PENDENT || cart.getCartStatus() == CartStatus.ERROR) {
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
	
	public void deleteCart(String cartId) throws NotFoundException {
		Cart cart = cartRepository.findByCartId(cartId);
		
		if(cart == null) throw new NotFoundException();
		
		cartRepository.delete(cart);
	}
	
}
