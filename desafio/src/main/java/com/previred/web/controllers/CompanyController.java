package com.previred.web.controllers;

import com.previred.web.dtos.CompanyDTO;
import com.previred.web.dtos.WorkerDTO;
import com.previred.web.services.CompanyServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController {


    private final CompanyServiceImpl companyServiceImpl;

    @Autowired
    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyServiceImpl = companyServiceImpl;
    }

    @PostMapping("/company")
    @Operation(summary = "Create a company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Company successfully created", content = @Content(examples = {
                    @ExampleObject(name = "createCompany",
                            summary = "Creates a company",
                            description = "Creates a company.",
                            value = "Company created")
            }, mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "409", description = "Company already exist", content = @Content(examples = {
                    @ExampleObject(name = "companyRegistered",
                            summary = "Shows that the company already exist",
                            description = "Shows that the Company already exist.",
                            value = "RUT already registered"),
                    @ExampleObject(name = "dataError",
                            summary = "Shows there is an error in the input data",
                            description = "Shows there is an error in the input data.",
                            value = "Error, verify the data")
            }, mediaType = MediaType.ALL_VALUE))})
    public ResponseEntity<Object> createCompany(@Parameter(description = "Company's RUT (only numbers)", example = "529736541")
                                                    @RequestParam(name = "rut") String rut,
                                                @RequestParam(name = "companyName") String companyName,
                                                @Parameter(description = "Creation date of the company (yyy-MM-dd)", example = "2019-02-16")
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return companyServiceImpl.createCompany(rut, companyName, date);

    }

    @GetMapping("/companies")
    @Operation(summary = "Get a list of all companies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Companies successfully retrieved", content = @Content(examples = {
                    @ExampleObject(name = "retrievesCompanies",
                            summary = "Retrieves a list of all companies",
                            description = "Retrieves a list of all companies.",
                            value = """
                                    [
                                        {
                                            "id": 15,
                                            "rut": null,
                                            "companyName": null,
                                            "date": "2021-03-07",
                                            "workers": []
                                        },
                                        {
                                            "id": 16,
                                            "rut": "123456789",
                                            "companyName": null,
                                            "date": "2021-03-07",
                                            "workers": []
                                        }
                                    ]""")
            }, mediaType = MediaType.ALL_VALUE))})
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        return companyServiceImpl.getCompanies();
    }

    @GetMapping("/company/{rut}")
    @Operation(summary = "Get a company by its RUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company successfully retrieved", content = @Content(examples = {
                    @ExampleObject(name = "retrievesCompany",
                            summary = "Retrieves a company",
                            description = "Retrieves a company.",
                            value = """
                                    [
                                        {
                                            "id": 15,
                                            "rut": "532876493",
                                            "companyName": "Xiaomi Chile",
                                            "date": "2019-07-12",
                                            "workers": []
                                         
                                        }
                                                                  
                                    ]""")
            }, mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(examples = {
                    @ExampleObject(name = "companyRegistered",
                            summary = "Shows that the company has not been found",
                            description = "Shows that the company has not been found.",
                            value = "Company not found")
            }, mediaType = MediaType.ALL_VALUE))})
    public ResponseEntity<?> getCompany(@Parameter(description = "Company's RUT (only numbers)", example = "529736541")
                                            @PathVariable String rut){
        return companyServiceImpl.getCompany(rut);
    }


    @PutMapping("/company/{rut}")
    @Operation(summary = "Update a company by its RUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company successfully updated", content = @Content(examples = {
                    @ExampleObject(name = "updateCompany",
                            summary = "Updates a company",
                            description = "Updates a company.",
                            value = "Company updated! Company{rut='503472385', companyName='Motorola', date=1994-10-13}")
            }, mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(examples = {
                    @ExampleObject(name = "companyRegistered",
                            summary = "Shows that the company has not been found",
                            description = "Shows that the company has not been found.",
                            value = "Company not found")
            }, mediaType = MediaType.ALL_VALUE))})
    public ResponseEntity<Object> updateCompany(@Parameter(description = "Company's RUT (only numbers)", example = "529736541")
                                                    @PathVariable String rut,
                                                @RequestBody CompanyDTO companyDTO) {

        return companyServiceImpl.updateCompany(rut, companyDTO);

    }

    @DeleteMapping("/company/{rut}")
    @Operation(summary = "Delete a company by its Rut")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company successfully deleted", content = @Content(examples = {
                    @ExampleObject(name = "createCompany",
                            summary = "Deletes a company",
                            description = "Deletes a company.",
                            value = "Company deleted")
            }, mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(examples = {
                    @ExampleObject(name = "companyRegistered",
                            summary = "Shows that the company has not been found",
                            description = "Shows that the company has not been found.",
                            value = "Company not found")
            }, mediaType = MediaType.ALL_VALUE))})
    public ResponseEntity<Object> deleteCompany(@Parameter(description = "Company's RUT (only numbers)", example = "529736541")
                                                    @PathVariable String rut) {

        return companyServiceImpl.deleteCompany(rut);
    }

    @PostMapping("/worker/company")
    @Operation(summary = "Add a worker to a company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Worker successfully added to the company", content = @Content(examples = {
                    @ExampleObject(name = "companyRegistered",
                            summary = "Worker successfully added to the company",
                            description = "Worker successfully added to the company.",
                            value = "Worker added to Company{rut='423456789', companyName='Apple', date=2021-03-07}")
            }, mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(examples = {
                    @ExampleObject(name = "companyRegistered",
                            summary = "Shows that the company has not been found",
                            description = "Shows that the company has not been found.",
                            value = "Company not found")
            }, mediaType = MediaType.ALL_VALUE))})
    public ResponseEntity<Object> addWorker(@Parameter(description = "Company's RUT (only numbers)", example = "529736541")
                                                @RequestParam(name = "rut") String rut,
                                            @RequestBody WorkerDTO workerDTO) {

        return companyServiceImpl.addWorker(rut, workerDTO);
    }
}
