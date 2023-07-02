package com.previred.crud.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;




@Service
public class EmployeeService {
	
	HashMap<String, Object> datos;
	
	private final EmployeeRepository employeeRepository;
	//Se crea la función que obtendrá las dependencias de EmployeeRepository
	//Para ser inyectadas en el EmployeeController	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	
	//Servicio para buscar el listado de empleados
	public List<Employee> getEmployees(){
		return this.employeeRepository.findAll();
	}
	
	//Servicio para buscar un empleado por el Id
	public Employee getEmployeeById( Long Id) {
		return this.employeeRepository.findById(Id).get();
	}
	
	
	//Servicio para crear un empleado
	public ResponseEntity<Object> createEmployee(Employee employee) {
		Optional<Employee> res = employeeRepository.findEmployeeByRut(employee.getRut());
		datos = new HashMap<>();
		//Se crea una condicion donde se buscara si existe el rut
		//Si existe un rut no se creara el empleado 
		if(res.isPresent()) {
			datos.put("error", true);
			datos.put("message", "El rut ingresado ya existe");
			return new ResponseEntity<>(
						datos,
						HttpStatus.CONFLICT
			);
		}
		employeeRepository.save(employee);
			datos.put("data", datos);
			datos.put("message", "El empleado se ha guardado con exito");
			return new ResponseEntity<>(
				employee,
				HttpStatus.CREATED
					);
	}
	//Servicio para editar un empleado
	public ResponseEntity<Object> updateEmployee(Employee employee) {
		Optional<Employee> res = employeeRepository.findEmployeeByRut(employee.getRut());
		datos = new HashMap<>();
		
		if(res.isPresent()) {
			employeeRepository.save(employee);
			datos.put("data", datos);
			datos.put("message", "El empleado se ha guardado con exito");
			return new ResponseEntity<>(
				employee,
				HttpStatus.CREATED
					);
		}else {
			datos.put("error", true);
			datos.put("message", "El rut ingresado no existe");
			return new ResponseEntity<>(
						datos,
						HttpStatus.CONFLICT
					);
		}
			
		}
		

	//Servicio para eliminar un empleado por el id
	public ResponseEntity<Object> deleteEmployee(Long id) {
		datos = new HashMap<>();
		boolean exists =  this.employeeRepository.existsById(id);
		if(!exists) {
			datos.put("error", true);
			datos.put("message", "El empleado no existe");
			return new ResponseEntity<>(
						datos,
						HttpStatus.CONFLICT
					);
		}
		employeeRepository.deleteById(id);
		datos.put("message", "empleado eliminado");
		return new ResponseEntity<>(
					datos,
					HttpStatus.ACCEPTED
				);
	}
}