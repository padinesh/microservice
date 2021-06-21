package com.dinesh.microservice2.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreaker2 {

	private Logger logger = LoggerFactory.getLogger(CircuitBreaker2.class);

	@GetMapping(value = "/circuitBreaker2")
	//@Retry(name = "Default") 3 times
	 @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	// @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
	// @RateLimiter(name="default")
	// @Bulkhead(name="sample-api")
	// 10s => 10000 calls to the sample api
	public String testCircuitBreaker() {
		logger.info("Sample api call received");
		String response = new RestTemplate().getForEntity("http://localhost:8080/dinesh", String.class).getBody();
		return response;

	}
	
	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}
}
