package com.TrueLearn.Cart.client.payload;

import java.math.BigDecimal;
import java.util.UUID;

import com.TrueLearn.Cart.model.CourseCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CourseResponse {
	
	private Long id;
	
	private UUID courseId;
	
	private String name;
	
	private CourseCategory courseCategory;
	
	private String instructor;
	
	private String description;
	
	private BigDecimal price;
	
	private Integer workload;

}
