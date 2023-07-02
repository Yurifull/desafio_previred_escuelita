package com.previred.crud.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	

//Función para obtener los servicios de EmployeeService	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
//Obtener la lista de empleados
  @GetMapping 
  	public List<Employee> getEmployees(){
	  return employeeService.getEmployees();
  }
//Obtener un empleado por el id
  @GetMapping("/{id}")
  public Employee getEmployeeById(@PathVariable Long id) {
    return employeeService.getEmployeeById(id);
  }
//Agregar un nuevo empleado 
  @PostMapping
  	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
	  return this.employeeService.createEmployee(employee);
  }
  
//Editar la información de un empleado
  @PutMapping 
	  public ResponseEntity<Object> updateEmployee( @RequestBody Employee employee ){ 
		return this.employeeService.updateEmployee(employee); 
  }
	 
//Eliminar un empleado
  @DeleteMapping(path = "{employeeId}")
  public ResponseEntity<Object> deleteEmployee(@PathVariable("employeeId") Long id){
	  return this.employeeService.deleteEmployee(id);
  }
}