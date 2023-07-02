package com.previred.crud.employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	//Query para buscar un empleado por el rut
	Optional<Employee> findEmployeeByRut(String rut);
}