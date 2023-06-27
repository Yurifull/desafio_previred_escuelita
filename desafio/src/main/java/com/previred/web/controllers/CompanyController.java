package com.previred.web.controllers;

import com.previred.web.dtos.CompanyDTO;
import com.previred.web.models.Company;
import com.previred.web.services.CompanyServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController {

    private final CompanyServiceImpl companyServiceImpl;

    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyServiceImpl = companyServiceImpl;
    }

    @PostMapping("/company")
    public ResponseEntity<Object> createCompany(@RequestParam(name = "rut") String rut,
                                                @RequestParam(name = "razonSocial") String razonSocial,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return companyServiceImpl.createCompany(rut, razonSocial, date);

    }

    @GetMapping("/companies")
    public List<CompanyDTO> getCompanies() {
        return companyServiceImpl.getCompanies();
    }

    @GetMapping("/company/{rut}")
    public ResponseEntity<?> getCompany(@PathVariable String rut){
        return companyServiceImpl.getCompany(rut);
    }

    @PutMapping("/company/{rut}")
    public ResponseEntity<Object> updateCompany(@PathVariable String rut,
                                                @RequestBody Company company) {

        return companyServiceImpl.updateCompany(rut, company);

    }

    @DeleteMapping("/company/{rut}")
    public ResponseEntity<Object> deleteCompany(@PathVariable String rut) {

        return companyServiceImpl.deleteCompany(rut);
    }
}
