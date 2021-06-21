package com.dinesh.microservice1.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dinesh.microservice1.model.Address;

@FeignClient(name = "microservice2")
public interface AddressProxy {
	
	@GetMapping(value = "/address/city/{city}")
	public Address displayAddressByName(@PathVariable String city);
	
	@GetMapping(value = "/circuitBreaker2")
	public String testCircuitBreaker();
}
