package com.TrueLearn.Cart.usecases;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface IDeleteCartUseCase {
	
	void deleteCart(String cartId) throws NotFoundException;

}
