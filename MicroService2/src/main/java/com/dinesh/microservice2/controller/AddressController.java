package com.dinesh.microservice2.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.microservice2.model.Address;
import com.dinesh.microservice2.service.AddressService;

@RestController
public class AddressController {
	
	private Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	Environment environment;
	
	@GetMapping(value = "/address")
	public List<Address> display(){
		return addressService.getAddress();
	}
	
	@GetMapping(value = "/address/{rollNumber}")
	public Address displayAddress(@PathVariable int rollNumber) {
		Address address = addressService.getAddressByStudentId(rollNumber);
		System.out.println(environment.getProperty("local.server.port"));
		address.setPort(environment.getProperty("local.server.port"));
		return address;
	}
	
	@GetMapping(value = "/address/city/{city}")
	public Address displayAddressByName(@PathVariable String city) {
		Address address =  addressService.getAddressByCity(city);
		address.setPort(environment.getProperty("local.server.port"));
		logger.info("{}", address);
		return address;
	}
	
}
