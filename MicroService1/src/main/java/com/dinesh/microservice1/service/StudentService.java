package com.dinesh.microservice1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinesh.microservice1.model.Student;
import com.dinesh.microservice1.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getStudent() {
		return studentRepository.findAll();
	}

	public Student getStudentById(int rollNumber) {
		return studentRepository.findById(rollNumber).get();
	}
}
