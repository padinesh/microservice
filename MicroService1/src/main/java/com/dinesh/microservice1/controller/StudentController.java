package com.dinesh.microservice1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dinesh.microservice1.model.Address;
import com.dinesh.microservice1.model.Student;
import com.dinesh.microservice1.service.AddressProxy;
import com.dinesh.microservice1.service.StudentService;

@RestController
public class StudentController {
	
	private Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}
	
	@Autowired
	StudentService studentService;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	AddressProxy addressProxy;
	@Autowired
	Environment environment;
	
	@GetMapping(value = "/students")
	public List<Student> display(){
		List<Student> studentList =  studentService.getStudent();
		
		  for(Student s : studentList)
		  s.setPort(environment.getProperty("local.server.port"));
		 
		return studentList;
		 
	}
	
	@GetMapping(value = "/students/{rollNumber}")
	public Student displayAddress(@PathVariable int rollNumber) {
		Student student = studentService.getStudentById(rollNumber);
		int roll = rollNumber;
		ResponseEntity<Address> address = restTemplate.getForEntity("http://localhost:8082/address/{rollNumber}", Address.class, roll);
		student.setAddress(address.getBody());
		student.setPort(environment.getProperty("local.server.port"));
		return student;
	}
	
	@GetMapping(value = "/students/{rollNumber}/city/{city}")
	public Student displayAddressByCity(@PathVariable int rollNumber, @PathVariable String city) {
		Student student = studentService.getStudentById(rollNumber);
		String cities = city;
		ResponseEntity<Address> address = restTemplate.getForEntity("http://localhost:8082/address/city/{city}", Address.class, cities);
		student.setAddress(address.getBody());
		student.setPort(environment.getProperty("local.server.port"));
		return student;
	}
	
	@GetMapping(value = "/students-feign/{rollNumber}/city/{city}")
	public Student displayAddressByCityProxy(@PathVariable int rollNumber, @PathVariable String city) {
		Student student = studentService.getStudentById(rollNumber);
		Address address = addressProxy.displayAddressByName(city);
		student.setAddress(address);
		student.setPort(environment.getProperty("local.server.port"));
		logger.info("{}", student);
		return student;
	}
}
