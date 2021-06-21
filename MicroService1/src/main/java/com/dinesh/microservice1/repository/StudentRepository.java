package com.dinesh.microservice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dinesh.microservice1.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
