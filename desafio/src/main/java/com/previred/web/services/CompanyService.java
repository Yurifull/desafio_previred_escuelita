package com.previred.web.services;

import com.previred.web.dtos.CompanyDTO;
import com.previred.web.dtos.WorkerDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface CompanyService {

    ResponseEntity<Object> createCompany(String rut, String companyName, LocalDate date);

    ResponseEntity<List<CompanyDTO>> getCompanies();

    ResponseEntity<?> getCompany(String rut);

    ResponseEntity<Object> updateCompany(String rut, CompanyDTO companyDTO);

    ResponseEntity<Object> deleteCompany(String rut);

    ResponseEntity<Object> addWorker(String rut, WorkerDTO workerDTO);

}
