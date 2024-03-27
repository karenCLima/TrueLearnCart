package com.TrueLearn.Cart.usecases.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.TrueLearn.Cart.model.*;
import com.TrueLearn.Cart.Util.CartConvert;
import com.TrueLearn.Cart.client.CourseClient;
import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.repository.CartRepository;
import com.TrueLearn.Cart.usecases.IUpdateCartUseCase;

@Service
public class UpdateCartUseCaseImpl implements IUpdateCartUseCase {
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	private CourseClient courseCliente;


	@Override
	public CartResponse updateCart(Cart cart) throws NotFoundException {
		Cart existCart = cartRepository.findByCartId(cart.getCartId());
		if(existCart == null) throw new NotFoundException();
		
		if(existCart.getCartStatus() == CartStatus.ERROR) {
			List<UUID> newCoursesIdsList = cart.getCoursesIdsList();
			BigDecimal newTotalPrice = newCoursesIdsList.stream().map(idCourse -> courseCliente.getCourseByCourseId(idCourse).getPrice())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			existCart.setCoursesIdsList(newCoursesIdsList);
			existCart.setPurchaseDate(LocalDateTime.now());
			existCart.setTotalPrice(newTotalPrice);
			existCart.setCartStatus(CartStatus.IN_PROGRESS);
			
		}else {
			throw new RuntimeException("Não é possível atualizar carrinho sem status de erro");
		}
		
		CartResponse cartResponse = CartConvert.toResponse(cartRepository.save(existCart));
		 
		return cartResponse;
	}

}
