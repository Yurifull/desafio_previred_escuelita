package com.previred.web.services;

import com.previred.web.dtos.CompanyDTO;
import com.previred.web.models.Company;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface CompanyService {

    ResponseEntity<Object> createCompany(String rut, String razonSocial, LocalDate date);

    List<CompanyDTO> getCompanies();

    ResponseEntity<Object> updateCompany(Company company);

    ResponseEntity<Object> deleteCompany(String rut);

}
