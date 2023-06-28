package com.previred.desafio_escuelita.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.previred.desafio_escuelita.model.Company;
import com.previred.desafio_escuelita.repository.CompanyRepository;

/**
 * The `CompanyService` class provides methods for saving, updating, deleting, and retrieving company
 * data from a database.
 */
@Service
@Transactional
public class CompanyService {
    private CompanyRepository companyRepository;

    // The `public CompanyService(CompanyRepository companyRepository)` is a constructor for the
    // `CompanyService` class. It takes a `CompanyRepository` object as a parameter and assigns it to
    // the `companyRepository` field of the `CompanyService` class. This allows the `CompanyService`
    // class to have access to the methods provided by the `CompanyRepository` class, such as saving,
    // updating, deleting, and retrieving company data from the database.
    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    /**
     * The saveCompany function saves a new company into the database.
     * 
     * @param company The company object that needs to be saved into the database.
     */
    public void saveCompany (Company company){
        this.companyRepository.save(company);
    }

    /**
     * The function allows for updating company data in a Java program.
     * 
     * @param company The "company" parameter is an object of the Company class. It represents the
     * company data that needs to be updated.
     */
    public void updateCompany (Company company){
        this.companyRepository.save(company);
    }

    /**
     * The deleteCompany function deletes all company data based on the provided id.
     * 
     * @param id The id parameter is an Integer that represents the unique identifier of the company
     * that needs to be deleted.
     */
    public void deleteCompany (Integer id){
        this.companyRepository.deleteById(id);
    }

    /**
     * The function returns all the data of companies from the company repository.
     * 
     * @return a list of Company objects.
     */
    public List<Company> findAll(){
        return companyRepository.findAll();
    }
}
