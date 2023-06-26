package com.previred.web.dtos;

import com.previred.web.models.Company;
import com.previred.web.models.Worker;

public class WorkerDTO {

    private Long id;

    private String rut;

    private String firstName;

    private String lastName;

    private String secondLastName;

    private String address;

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

    public Long getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
