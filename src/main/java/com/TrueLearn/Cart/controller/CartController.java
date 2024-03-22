package com.TrueLearn.Cart.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TrueLearn.Cart.dto.CartRequest;
import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.usecases.IAddProductUseCase;
import com.TrueLearn.Cart.usecases.ICreateCartUseCase;
import com.TrueLearn.Cart.usecases.IDeleteProductUseCase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/cart")
public class CartController{
	
	@Autowired
	ICreateCartUseCase createCartService;
	
	@Autowired
	IAddProductUseCase addProductService;
	
	@Autowired
	IDeleteProductUseCase deleteProductService;
	
	@PostMapping
	public ResponseEntity<CartResponse> createCart(@RequestBody CartRequest cartRequest) throws NotFoundException{
		CartResponse cartResponse = createCartService.createCart(cartRequest);
		return ResponseEntity.created(URI.create("/cart/" + cartResponse.getCartId())).body(cartResponse);
	}
	
	@DeleteMapping("/{cartId}")
	public void deleteCart(@PathVariable String cartId) throws NotFoundException {
		deleteProductService.deleteCart(cartId);
	}
	
	@DeleteMapping("/product/{cartId}")
	public ResponseEntity<CartResponse> deleteProductInCart(@PathVariable String cartId, @RequestBody UUID courseId) throws Exception{
		return ResponseEntity.ok(deleteProductService.deleteProduct(cartId, courseId));
	}
}
