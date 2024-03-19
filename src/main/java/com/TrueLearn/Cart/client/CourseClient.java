package com.TrueLearn.Cart.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.TrueLearn.Cart.client.payload.CourseResponse;


@FeignClient(name = "CourseCliente", url = "http://localhost:8082/course")
public interface CourseClient {
	
	@RequestMapping(method = RequestMethod.GET,value = "/find/{courseId}")
	CourseResponse getCourseByCourseId(@PathVariable UUID courseId);

}
