package com.dinesh.microservice2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dinesh.microservice2.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

	Optional<Address> findByCity(String city);

}
