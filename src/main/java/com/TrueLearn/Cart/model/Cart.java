package com.TrueLearn.Cart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.TrueLearn.Cart.client.payload.CourseResponse;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="carts")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cartId")
	private String cartId;
	
	@Column(name = "purchaseDate")
	private LocalDateTime purchaseDate;

	@Column(name = "totalPrice")
    private BigDecimal totalPrice;

	@Column(name = "cartStatus")
    private CartStatus cartStatus;
	
	@ElementCollection
	@CollectionTable(name = "courses-cart", joinColumns = @JoinColumn(name = "cart_id"))
	@Column(name = "courseId")
	private List<UUID> coursesIdsList;

}
