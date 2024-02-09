package com.TrueLearn.Cart.client;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.TrueLearn.Cart.client.payload.CourseResponse;



public interface CourseClient {
	
	@GetExchange(value = "/find/{courseId}")
	CourseResponse getCourseByCourseId(@PathVariable UUID courseId);

}
