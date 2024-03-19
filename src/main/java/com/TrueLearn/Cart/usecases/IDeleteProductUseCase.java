package com.TrueLearn.Cart.usecases;

import java.util.UUID;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.TrueLearn.Cart.dto.CartResponse;

public interface IDeleteProductUseCase {
	
	CartResponse deleteProduct(String cartId, UUID courseId) throws Exception;
	
	void deleteCart(String cartId) throws NotFoundException;

}
