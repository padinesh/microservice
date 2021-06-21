package com.dinesh.microservice1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.microservice1.service.AddressProxy;

@RestController
public class CircuitBreakerController {
	
	@Autowired
	AddressProxy addressProxy;
	
	@GetMapping(value = "/circuitBreaker")
	public String TestCircuitBreaker() {
		String response = addressProxy.testCircuitBreaker();
		return response;
		
	}
}
