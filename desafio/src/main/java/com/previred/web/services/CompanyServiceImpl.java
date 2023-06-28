package com.previred.web.services;

import com.previred.web.dtos.CompanyDTO;
import com.previred.web.dtos.WorkerDTO;
import com.previred.web.models.Company;
import com.previred.web.models.Worker;
import com.previred.web.repositories.CompanyRepository;
import com.previred.web.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final WorkerRepository workerRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, WorkerRepository workerRepository) {
        this.companyRepository = companyRepository;
        this.workerRepository = workerRepository;
    }

    //Creates a company with the parameters if the given RUT is not already registered.
    @Override
    public ResponseEntity<Object> createCompany(String rut, String companyName, LocalDate date) {

        if (companyRepository.existsCompanyByRut(rut)) {

            return new ResponseEntity<>("Rut already registered", HttpStatus.CONFLICT);

        } else if (!rut.isEmpty() && !companyName.isEmpty()) {

            Company company = new Company(rut, companyName, date);
            companyRepository.save(company);
            return new ResponseEntity<>("Company created", HttpStatus.CREATED);

        } else {

            return new ResponseEntity<>("Error, verify the data", HttpStatus.CONFLICT);

        }
    }

    //Get a list of all companies even if there are none.
    @Override
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        List<CompanyDTO> companies = companyRepository.findAll().stream().map(CompanyDTO::new).toList();
        return new ResponseEntity<>(companies,HttpStatus.OK);
    }

    //Get a company by its RUT, If it exists, returns the data.
    @Override
    public ResponseEntity<?> getCompany(String rut) {

        if (companyRepository.existsCompanyByRut(rut)) {
            Company company = companyRepository.findCompanyByRut(rut);
            CompanyDTO companyDTO = companyRepository.findById(company.getId()).map(CompanyDTO::new).orElse(null);
            return new ResponseEntity<>(companyDTO, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
    }

    //Updates the company associated with the given rut, if the company exists, updates.
    @Override
    public ResponseEntity<Object> updateCompany(String rut, CompanyDTO companyDTO) {
        Company company = companyRepository.findCompanyByRut(rut);

        if(company != null){
            company.setRut(companyDTO.getRut());
            company.setCompanyName(companyDTO.getCompanyName());
            companyRepository.save(company);
            return new ResponseEntity<>("Company updated! " + company, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
    }

    //Deletes a company by its RUT if repository can find it in DB.
    @Override
    public ResponseEntity<Object> deleteCompany(String rut) {
        Company company = companyRepository.findCompanyByRut(rut);
        if (company != null) {
            companyRepository.deleteById(company.getId());
            return new ResponseEntity<>("Company deleted", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
    }

    //If the given company RUT is valid, it creates a new worker and adds it to the company.
    @Override
    public ResponseEntity<Object> addWorker(String rut, WorkerDTO workerDTO) {
        Company company = companyRepository.findCompanyByRut(rut);
        if (company != null) {
            Worker worker = new Worker(workerDTO.getRut(), workerDTO.getFirstName(), workerDTO.getLastName(), workerDTO.getSecondLastName(), workerDTO.getAddress());
            worker.setCompany(company);
            workerRepository.save(worker);
            return new ResponseEntity<>("Worker added to " + company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
    }
}
