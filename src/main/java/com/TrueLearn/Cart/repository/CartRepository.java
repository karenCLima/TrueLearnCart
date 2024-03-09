package com.TrueLearn.Cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TrueLearn.Cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	@Query(value = "SELECT * FROM CARTS WHERE CART_ID = :cartId", nativeQuery = true)
	Cart findByCartId(String cartId);
	

}
