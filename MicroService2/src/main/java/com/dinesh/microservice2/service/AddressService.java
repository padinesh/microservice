package com.dinesh.microservice2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinesh.microservice2.model.Address;
import com.dinesh.microservice2.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	public List<Address> getAddress() {
		return addressRepository.findAll();
	}

	public Address getAddressByStudentId(int rollNumber) {
		return addressRepository.findById(rollNumber).get();
	}

	public Address getAddressByCity(String city) {
		return addressRepository.findByCity(city).get();
	}
	
	
}
