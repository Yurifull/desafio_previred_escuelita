package com.previred.web.services;

import com.previred.web.dtos.CompanyDTO;
import com.previred.web.models.Company;
import com.previred.web.repositories.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public ResponseEntity<Object> createCompany(String rut, String razonSocial, LocalDate date) {

        if(companyRepository.existsByRut(rut)) {

            return new ResponseEntity<>("Rut already registered", HttpStatus.FORBIDDEN);

        } else if (!rut.isEmpty() && !razonSocial.isEmpty()) {

            Company company = new Company(rut, razonSocial, date);
            companyRepository.save(company);
            return new ResponseEntity<>("Company created", HttpStatus.CREATED);

        } else {

            return new ResponseEntity<>("Error, verify the data", HttpStatus.FORBIDDEN);

        }
    }

    @Override
    public List<CompanyDTO> getCompanies() {
        return companyRepository.findAll().stream().map(CompanyDTO::new).collect(toList());
    }

    @Override
    public ResponseEntity<Object> updateCompany(Company company) {
        if (companyRepository.existsById(company.getId())) {

            companyRepository.save(company);
            return new ResponseEntity<>("Company updated", HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Company does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> deleteCompany(String rut) {
        if(companyRepository.existsByRut(rut)){
            companyRepository.deleteByRut(rut);
            return new ResponseEntity<>("Company deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
