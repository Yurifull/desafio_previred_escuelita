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
@RequestMapping("/company")
public class CompanyController {

    private final CompanyServiceImpl companyServiceImpl;

    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyServiceImpl = companyServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCompany(@RequestParam(name = "rut") String rut,
                                                @RequestParam(name = "razonSocial") String razonSocial,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return companyServiceImpl.createCompany(rut, razonSocial, date);

    }

    @GetMapping("/get")
    public List<CompanyDTO> getCompanies() {
        return companyServiceImpl.getCompanies();
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateCompany(@RequestBody Company company) {

        return companyServiceImpl.updateCompany(company);

    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteCompany(@RequestParam(name = "rut") String rut) {

        return companyServiceImpl.deleteCompany(rut);
    }
}
