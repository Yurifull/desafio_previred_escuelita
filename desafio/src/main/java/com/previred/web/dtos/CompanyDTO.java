package com.previred.web.dtos;

import com.previred.web.models.Company;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyDTO {

    private Long id;

    private String rut;

    private String razonSocial;

    private LocalDate date;

    private List<WorkerDTO> workers;

    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.rut = company.getRut();
        this.razonSocial = company.getRazonSocial();
        this.date = company.getDate();
        this.workers = company.getWorkers().stream().map(WorkerDTO::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<WorkerDTO> getWorkers() {
        return workers;
    }

    public void setWorkers(List<WorkerDTO> workers) {
        this.workers = workers;
    }
}
