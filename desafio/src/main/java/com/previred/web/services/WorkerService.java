package com.previred.web.services;

import com.previred.web.dtos.WorkerDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WorkerService {

    ResponseEntity<Object> createWorker(String rut, String firstName, String lastName, String secondLastName, String address, String companyRut);

    ResponseEntity<List<WorkerDTO>> getWorkers();

    ResponseEntity<?> getWorker(String rut);

    ResponseEntity<Object> updateWorker(String rut, WorkerDTO workerDTO, String companyRut);

    ResponseEntity<Object> deleteWorker(String rut);
}
