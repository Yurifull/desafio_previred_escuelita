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

import com.previred.desafio_escuelita.model.Company;
import com.previred.desafio_escuelita.service.CompanyService;



/**
 * The `CompanyController` class is a Java controller that handles HTTP requests related to managing
 * company data, such as saving, updating, deleting, and retrieving companies.
 */
@Controller
@CrossOrigin("*")
@RestController
@RequestMapping("/company")
public class CompanyController {
    private CompanyService companyService;

    // The code `public CompanyController(@Autowired CompanyService companyService)` is a constructor
    // for the `CompanyController` class. It is using the `@Autowired` annotation to automatically
    // inject an instance of the `CompanyService` class into the `companyService` field of the
    // `CompanyController` class. This allows the `CompanyController` class to have access to the
    // methods and functionality provided by the `CompanyService` class.
    public CompanyController(@Autowired CompanyService companyService){
        this.companyService = companyService;
    }

    /**
     * The `saveCompany` function in the Java code allows for saving a new company into a database.
     * 
     * @param company The parameter "company" is of type Company and represents the company object that
     * needs to be saved into the database. It is annotated with @RequestBody, which means that the
     * company object will be deserialized from the request body.
     */
    @PostMapping("/save")
    public void saveCompany (@RequestBody Company company){
        companyService.saveCompany(company);
    }

    /**
     * The function allows updating company data by accepting a Company object as a request body.
     * 
     * @param company The parameter "company" is of type Company and represents the updated company
     * data that will be used to update the existing company record in the database.
     */
    @PutMapping("/update")
    public void updateCompany (@RequestBody Company company){
        companyService.updateCompany(company);
    }

    /**
     * The above function is a DELETE request mapping in Java that deletes a company's data based on
     * the provided ID.
     * 
     * @param id The id parameter is the unique identifier of the company that needs to be deleted.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteCompany (@PathVariable Integer id){
        companyService.deleteCompany(id);
    }

    /**
     * The above function is a GET request mapping that returns a list of all companies.
     * 
     * @return The findAll() method is returning a List of Company objects.
     */
    @GetMapping("/findAll")
    public List<Company> findAll(){
        return companyService.findAll();
    }
}
