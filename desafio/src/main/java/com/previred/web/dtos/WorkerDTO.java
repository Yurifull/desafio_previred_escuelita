package com.previred.web.dtos;

import com.previred.web.models.Company;
import com.previred.web.models.Worker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class WorkerDTO {

    @Schema(hidden = true)
    private Long id;

    @Schema(description = "Worker's RUT", example = "123456789")
    private String rut;

    @Schema(description = "First name", example = "Camilo")
    private String firstName;

    @Schema(description = "Last name", example = "Quezada")
    private String lastName;

    @Schema(description = "Second last name", example = "Urrutia")
    private String secondLastName;

    @Schema(description = "Address", example = "Balmaceda 1504, Rancagua")
    private String address;

    @Schema(hidden = true)
    private Company company;

    public WorkerDTO (Worker worker) {
        this.id = worker.getId();
        this.rut = worker.getRut();
        this.firstName = worker.getFirstName();
        this.lastName = worker.getLastName();
        this.secondLastName = worker.getSecondLastName();
        this.address = worker.getAddress();
        this.company = worker.getCompany();
    }

}
