package com.TrueLearn.Cart.usecases;

import java.util.UUID;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.TrueLearn.Cart.dto.CartResponse;

public interface IAddProductUseCase {
	
	CartResponse AddProduct(String cartId, UUID courseId) throws NotFoundException;

}
