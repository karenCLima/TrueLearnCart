package com.TrueLearn.Cart.usecases;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.TrueLearn.Cart.dto.CartRequest;
import com.TrueLearn.Cart.dto.CartResponse;

public interface IAddProductUseCase {
	
	CartResponse AddProduct(CartRequest cartRequest) throws NotFoundException;

}
