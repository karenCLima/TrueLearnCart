package com.TrueLearn.Cart.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TrueLearn.Cart.dto.CartRequest;
import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.usecases.IAddProductUseCase;
import com.TrueLearn.Cart.usecases.IDeleteProductUseCase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/cart")
public class CartController{
	
	@Autowired
	IAddProductUseCase addProductService;
	
	@Autowired
	IDeleteProductUseCase deleteProductService;
	
	@PutMapping
	public ResponseEntity<CartResponse> addProductInCart(@RequestBody CartRequest cartRequest) throws NotFoundException{
		return ResponseEntity.ok(addProductService.AddProduct(cartRequest));
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
