package com.previred.web.dtos;

import com.previred.web.models.Company;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CompanyDTO {

    @Schema(hidden = true)
    private Long id;

    @Schema(description = "Company's RUT", example = "5234567891")
    private String rut;

    @Schema(description = "Company's name", example = "Perived Co.")
    private String companyName;

    @Schema(description = "Creation date", example = "2002-03-24")
    private LocalDate date;

    @Schema(hidden = true)
    private List<WorkerDTO> workers;

    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.rut = company.getRut();
        this.companyName = company.getCompanyName();
        this.date = company.getDate();
        this.workers = company.getWorkers().stream().map(WorkerDTO::new).collect(Collectors.toList());
    }

}
