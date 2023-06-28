package com.previred.desafio_escuelita.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.previred.desafio_escuelita.model.Employee;
import com.previred.desafio_escuelita.repository.EmployeeRepository;

/**
 * The `EmployeeService` class provides methods for saving, updating, deleting, and retrieving
 * employees from the employee repository.
 */
@Service
@Transactional
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    // The code `public EmployeeService(EmployeeRepository employeeRepository)` is a constructor for
    // the `EmployeeService` class. It takes an instance of `EmployeeRepository` as a parameter and
    // assigns it to the `employeeRepository` field of the `EmployeeService` class. This allows the
    // `EmployeeService` class to have access to the methods and functionality provided by the
    // `EmployeeRepository` class.
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    /**
     * The function saves an employee object to the employee repository.
     * 
     * @param employee The employee object that needs to be saved in the employee repository.
     */
    public void saveEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }

    /**
     * The function updates an employee in the employee repository.
     * 
     * @param employee The employee object that needs to be updated in the employee repository.
     */
    public void updateEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }

    /**
     * The function deletes an employee from the employee repository based on their ID.
     * 
     * @param id The id parameter is an Integer that represents the unique identifier of the employee
     * to be deleted.
     */
    public void deleteEmployee(Integer id){
        this.employeeRepository.deleteById(id);
    }

    /**
     * The function returns a list of all employees.
     * 
     * @return A List of Employee objects is being returned.
     */
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }
}
