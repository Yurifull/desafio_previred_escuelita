package com.previred.desafio_escuelita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.previred.desafio_escuelita.model.Employee;

// This code is defining an interface called `EmployeeRepository` that extends the `JpaRepository`
// interface.
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
}
