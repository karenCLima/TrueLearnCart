package com.TrueLearn.Cart.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TrueLearn.Cart.dto.CartResponse;
import com.TrueLearn.Cart.model.Cart;
import com.TrueLearn.Cart.usecases.IAddProductUseCase;
import com.TrueLearn.Cart.usecases.ICreateCartUseCase;
import com.TrueLearn.Cart.usecases.IDeleteCartUseCase;
import com.TrueLearn.Cart.usecases.IDeleteProductUseCase;
import com.TrueLearn.Cart.usecases.IListCartUseCase;
import com.TrueLearn.Cart.usecases.IUpdateCartUseCase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/cart")
public class CartController{
	
	@Autowired
	IListCartUseCase listCartService;
	
	@Autowired
	ICreateCartUseCase createCartService;
	
	@Autowired
	IAddProductUseCase addProductService;
	
	@Autowired
	IDeleteCartUseCase deleteCartService;
	
	@Autowired
	IDeleteProductUseCase deleteProductService;
	
	@Autowired
	IUpdateCartUseCase updateCartService;
	
	@GetMapping
	public ResponseEntity<Page<CartResponse>> getAllCarts(
			@RequestParam(value = "page", required = false,defaultValue = "0") Integer page, 	
			@RequestParam(value = "size", required = false,defaultValue = "10") Integer size, 
			@RequestParam(value = "direction", required = false,defaultValue = "ASC") String direction){
		return ResponseEntity.ok(listCartService.getAllCarts(page, size, direction));
	}
	
	@PostMapping("/{userCpf}")
	public ResponseEntity<CartResponse> createCart(@PathVariable String userCpf) throws NotFoundException{
		CartResponse cartResponse = createCartService.createCart(userCpf);
		return ResponseEntity.created(URI.create("/cart/" + cartResponse.getCartId())).body(cartResponse);
	}
	
	@DeleteMapping("/{cartId}")
	public void deleteCart(@PathVariable String cartId) throws NotFoundException {
		deleteCartService.deleteCart(cartId);
	}
	
	@PutMapping
	public ResponseEntity<CartResponse> updateCart(@RequestBody Cart cart) throws NotFoundException{
		return ResponseEntity.ok(updateCartService.updateCart(cart));
	}
	
	@GetMapping("/{cartId}")
	public ResponseEntity<CartResponse> getCartById(@PathVariable String cartId) throws NotFoundException{
		return ResponseEntity.ok(listCartService.getCartByCartId(cartId));
	}
	
	@PostMapping("/product/{cartId}/{courseId}")
	public ResponseEntity<CartResponse> addProductInCart(@PathVariable String cartId, @PathVariable UUID courseId) throws Exception{
		return ResponseEntity.ok(addProductService.AddProduct(cartId, courseId));
	}
	
	@DeleteMapping("/product/{cartId}")
	public ResponseEntity<CartResponse> deleteProductInCart(@PathVariable String cartId, @RequestBody UUID courseId) throws Exception{
		return ResponseEntity.ok(deleteProductService.deleteProduct(cartId, courseId));
	}
}
