package com.TrueLearn.Cart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.TrueLearn.Cart.client.CourseClient;

@Configuration
public class WebClientConfig {
	
	@Value("${trueLearn.course.url}")
	private String courseUrl;
	
	@Bean
	WebClient webClient(){
		return WebClient.builder().baseUrl(courseUrl).build();
	}
	
	@Bean
    CourseClient courseClient (WebClient webClient){
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter
                        .forClient(webClient)).build();
        return httpServiceProxyFactory.createClient(CourseClient.class);
    }

}
