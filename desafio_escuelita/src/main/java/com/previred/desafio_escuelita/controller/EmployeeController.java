package com.previred.desafio_escuelita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.desafio_escuelita.model.Employee;
import com.previred.desafio_escuelita.service.EmployeeService;


/**
 * The `EmployeeController` class is a REST controller that handles HTTP requests related to employee
 * management, such as saving, updating, deleting, and retrieving employees.
 */

@Controller
@CrossOrigin("*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    // The code `public EmployeeController(@Autowired EmployeeService employeeService)` is a
    // constructor for the `EmployeeController` class. It is using the `@Autowired` annotation to
    // automatically inject an instance of the `EmployeeService` class into the `employeeService` field
    // of the `EmployeeController` class. This allows the `EmployeeController` class to have access to
    // the methods and functionality provided by the `EmployeeService` class.
    public EmployeeController(@Autowired EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    /**
     * The function saves an employee object by calling the saveEmployee method from the
     * employeeService.
     * 
     * @param employee The employee parameter is of type Employee and is annotated with @RequestBody.
     * This means that the employee object will be deserialized from the request body and bound to this
     * parameter.
     */
    @PostMapping("/save")
    public void saveEmployee (@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
    }

    /**
     * The above function updates an employee record in the database.
     * 
     * @param employee The employee parameter is of type Employee and is annotated with @RequestBody.
     * This means that the employee object will be deserialized from the request body and mapped to the
     * Employee class.
     */
    @PutMapping("/update")
    public void updateEmployee (@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
    }

    /**
     * This function deletes an employee with the specified ID.
     * 
     * @param id The "id" parameter is a path variable that represents the unique identifier of the
     * employee to be deleted.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteEmployee (@PathVariable Integer id){
        employeeService.deleteEmployee(id);
    }

    /**
     * The above function is a GET request mapping that returns a list of all employees.
     * 
     * @return The method is returning a List of Employee objects.
     */
    @GetMapping("/findAll")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
}
