package com.TrueLearn.Cart.model;

import java.math.BigDecimal;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="courses-cart")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "courseId")
	private UUID courseId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "category")
	private CourseCategory courseCategory;
	
	@Column(name = "instructor")
	private String instructor;
	
	@Column(name = "description", length=512)
	private String description;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "workload")
	private Integer workload;
	
	@ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
