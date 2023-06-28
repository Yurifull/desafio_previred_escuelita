package com.previred.web.controllers;

import com.previred.web.dtos.WorkerDTO;
import com.previred.web.services.WorkerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WorkerController {

    private final WorkerServiceImpl workerServiceImpl;

    @Autowired
    public WorkerController(WorkerServiceImpl workerServiceImpl) {
        this.workerServiceImpl = workerServiceImpl;
    }

    //Endpoint to create a worker
    @PostMapping("/worker")
    @Operation(summary = "Create a worker")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worker successfully created", content = @Content(examples = {
                    @ExampleObject(name = "createWorker",
                            summary = "Creates a worker",
                            description = "Creates a worker.",
                            value = "Worker updated! Worker{rut='123456789', firstName='Fabian', lastName='Quezada', secondLastName='Mendoza', address='Gran Avenida 12'}")
            }, mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "409", description = "Verify data", content = @Content(examples = {
                    @ExampleObject(name = "WorkerRegistered",
                            summary = "Shows that worker already exist",
                            description = "Shows that worker already exist.",
                            value = "Worker already exist"),
                    @ExampleObject(name = "updateWorker",
                            summary = "Shows there is an error in the input data",
                            description = "Shows there is an error in the input data.",
                            value = "Error, verify the data")
            }, mediaType = MediaType.ALL_VALUE))})
    public ResponseEntity<Object> createWorker(@Parameter(description = "Worker's RUT (only numbers)", example = "129736541")
                                               @RequestParam(name = "rut") String rut,
                                               @RequestParam(name = "firstName") String firstName,
                                               @RequestParam(name = "lastName") String lastName,
                                               @RequestParam(name = "secondLastName") String secondLastName,
                                               @RequestParam(name = "address") String address,
                                               @Parameter(description = "Company's RUT (only numbers)", example = "529736541")
                                               @RequestParam(name = "companyRut", required = false) String companyRut) {

        return workerServiceImpl.createWorker(rut, firstName, lastName, secondLastName, address, companyRut);
    }

    //Endpoint to get a list of all workers
    @GetMapping("/workers")
    @Operation(summary = "Get a list of all workers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workers successfully retrieved", content = @Content(examples = {
                    @ExampleObject(name = "updateWorker",
                            summary = "Retrieves a list of workers",
                            description = "Retrieves a list of workers.",
                            value = """
                                    [
                                        {
                                            "id": 1,
                                            "rut": "58762341",
                                            "firstName": "Fabian",
                                            "lastName": "Sepulveda",
                                            "secondLastName": "Retamal",
                                            "address": "Arturo Prat 1423",
                                            "company": {
                                                "id": 18,
                                                "rut": "548297532",
                                                "companyName": "Apple",
                                                "date": "2021-03-07"
                                            }
                                        },
                                        {
                                            "id": 4,
                                            "rut": "205664112",
                                            "firstName": "Cesar",
                                            "lastName": "Prat",
                                            "secondLastName": "Toloza",
                                            "address": "Camino agricola 123",
                                            "company": null
                                        }]""")
            }, mediaType = MediaType.ALL_VALUE))})
    public ResponseEntity<List<WorkerDTO>> getWorkers() {
        return workerServiceImpl.getWorkers();
    }

    //Endpoint to get a worker by its RUT
    @GetMapping("/worker/{rut}")
    @Operation(summary = "Get a worker by its RUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Worker successfully retrieved", content = @Content(examples = {
                    @ExampleObject(name = "retrieveWorker",
                            summary = "Retrieves a worker",
                            description = "Retrieves a worker.",
                            value = """
                                    {
                                        "id": 6,
                                        "rut": "198373643",
                                        "firstName": "Camila",
                                        "lastName": "Retamal",
                                        "secondLastName": "Salas",
                                        "address": "Concepción 534",
                                        "company": {
                                            "id": 20,
                                            "rut": "468739543",
                                            "companyName": "Samsung",
                                            "date": "2003-01-02"
                                        }
                                    }""")
            }, mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Worker not found", content = @Content(examples = {
                    @ExampleObject(name = "WorkerRegistered",
                            summary = "Shows that the worker has not been found",
                            description = "Shows that the worker has not been found.",
                            value = "Worker not found")
            }, mediaType = MediaType.ALL_VALUE))})
    public ResponseEntity<?> getWorker(@Parameter(description = "Worker's RUT (only numbers)", example = "129736541")
                                       @PathVariable String rut) {
        return workerServiceImpl.getWorker(rut);
    }

    //Endpoint to update a worker by its rut
    @PutMapping("/worker/{rut}")
    @Operation(summary = "Update a worker by its RUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Worker successfully updated", content = @Content(examples = {
                    @ExampleObject(name = "updateWorker",
                            summary = "Updates worker information",
                            description = "Updates worker information.",
                            value = "Worker updated! Worker{rut='123456789', firstName='Fabian', lastName='Quezada', secondLastName='Mendoza', address='Gran Avenida 12'}")
            }, mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Worker not found", content = @Content(examples = {
                    @ExampleObject(name = "workerNotUpdated",
                            summary = "Shows that the worker has not been found",
                            description = "Shows that the worker has not been found.",
                            value = "Worker not found")
            }, mediaType = MediaType.ALL_VALUE))})
    public ResponseEntity<Object> updateWorker(@Parameter(description = "Worker's RUT (only numbers)", example = "129736541")
                                               @PathVariable(name = "rut") String rut,
                                               @Parameter(description = "Company's RUT (only numbers ex.529736541)")
                                               @RequestParam(name = "companyRut", required = false) String companyRut,
                                               @RequestBody WorkerDTO workerDTO) {

        return workerServiceImpl.updateWorker(rut, workerDTO, companyRut);
    }

    //Endpoint to delete a worker by its RUT
    @DeleteMapping("/worker/{rut}")
    @Operation(summary = "Delete a worker by its RUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Worker successfully deleted", content = @Content(examples = {
                    @ExampleObject(name = "deleteWorker",
                            summary = "Deletes a worker",
                            description = "Deletes a worker.",
                            value = "Worker deleted")
            }, mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Worker not found", content = @Content(examples = {
                    @ExampleObject(name = "workerNotDeleted",
                            summary = "Shows that the worker has not been found",
                            description = "Shows that the worker has not been found.",
                            value = "Worker not found")
            }, mediaType = MediaType.ALL_VALUE))})
    public ResponseEntity<Object> deleteWorker(@Parameter(description = "Worker's RUT (only numbers)", example = "129736541")
                                               @PathVariable String rut) {

        return workerServiceImpl.deleteWorker(rut);
    }


}
