package com.previred.web.services;

import com.previred.web.dtos.WorkerDTO;
import com.previred.web.models.Company;
import com.previred.web.models.Worker;
import com.previred.web.repositories.CompanyRepository;
import com.previred.web.repositories.WorkerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final CompanyRepository companyRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository, CompanyRepository companyRepository) {
        this.workerRepository = workerRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public ResponseEntity<Object> createWorker(String rut, String firstName, String lastName, String secondLastName, String address, String companyRut) {

        if (workerRepository.existsByRut(rut)) {

            return new ResponseEntity<>("Rut already registered", HttpStatus.CONFLICT);

        } else if (!rut.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !address.isEmpty()) {

            Company company = companyRepository.findCompanyByRut(companyRut);
            Worker worker = new Worker(rut, firstName, lastName, secondLastName, address, company);
            workerRepository.save(worker);
            return new ResponseEntity<>("Worker created", HttpStatus.CREATED);

        } else {

            return new ResponseEntity<>("Error, verify the data", HttpStatus.CONFLICT);

        }
    }

    @Override
    public ResponseEntity<List<WorkerDTO>> getWorkers() {
        //return workerRepository.findAll().stream().map(WorkerDTO::new).collect(toList());
        List<WorkerDTO> workers = workerRepository.findAll().stream().map(WorkerDTO::new).toList();
        return new ResponseEntity<>(workers,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> getWorker(String rut) {

        if (workerRepository.existsByRut(rut)) {
            Worker worker = workerRepository.findWorkerByRut(rut);
            WorkerDTO workerDTO = workerRepository.findById(worker.getId()).map(WorkerDTO::new).orElse(null);
            return new ResponseEntity<>(workerDTO, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Worker does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> updateWorker(String rut, WorkerDTO workerDTO, String companyRut) {
        Worker worker = workerRepository.findWorkerByRut(rut);
        if (worker != null) {

            worker.setRut(workerDTO.getRut());
            worker.setFirstName(workerDTO.getFirstName());
            worker.setLastName(workerDTO.getLastName());
            worker.setSecondLastName(workerDTO.getSecondLastName());
            worker.setAddress(workerDTO.getAddress());
            if (companyRepository.existsByRut(companyRut)) {
                Company company = companyRepository.findCompanyByRut(companyRut);
                worker.setCompany(company);
            }
            workerRepository.save(worker);
            return new ResponseEntity<>("Worker updated! " + worker, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Worker does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> deleteWorker(String rut) {
        Worker worker = workerRepository.findWorkerByRut(rut);
        if (worker != null) {
            workerRepository.deleteById(worker.getId());
            return new ResponseEntity<>("Worker deleted", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Worker does not exists", HttpStatus.NOT_FOUND);
        }
    }
}
