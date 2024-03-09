package com.TrueLearn.Cart.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.TrueLearn.Cart.client.payload.CourseResponse;
import com.TrueLearn.Cart.client.payload.UserResponse;
import com.TrueLearn.Cart.model.CartStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CartResponse {
	
	@JsonProperty("card-id")
    @Schema(name = "card-id", example = "cb79646c-7de5-11ee-b962-0242ac120002")
	private String cartId;
	
	private LocalDateTime purchaseDate;

    private BigDecimal totalPrice;
    
    private CartStatus cartStatus;
    
    private UserResponse userResponse;
    
    private List<UUID> courseIds;

}
