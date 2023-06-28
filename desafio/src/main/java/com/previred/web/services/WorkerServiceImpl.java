package com.previred.web.services;

import com.previred.web.dtos.WorkerDTO;
import com.previred.web.models.Company;
import com.previred.web.models.Worker;
import com.previred.web.repositories.CompanyRepository;
import com.previred.web.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository, CompanyRepository companyRepository) {
        this.workerRepository = workerRepository;
        this.companyRepository = companyRepository;
    }

    //Creates a worker with the parameters if the given RUT is not already registered.
    @Override
    public ResponseEntity<Object> createWorker(String rut, String firstName, String lastName, String secondLastName, String address, String companyRut) {

        if (workerRepository.existsWorkerByRut(rut)) {

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

    //Get a list of all workers even if there are none.
    @Override
    public ResponseEntity<List<WorkerDTO>> getWorkers() {
        //return workerRepository.findAll().stream().map(WorkerDTO::new).collect(toList());
        List<WorkerDTO> workers = workerRepository.findAll().stream().map(WorkerDTO::new).toList();
        return new ResponseEntity<>(workers,HttpStatus.OK);

    }

    //Get a worker by its RUT, If it exists, returns the data.
    @Override
    public ResponseEntity<?> getWorker(String rut) {

        if (workerRepository.existsWorkerByRut(rut)) {
            Worker worker = workerRepository.findWorkerByRut(rut);
            WorkerDTO workerDTO = workerRepository.findById(worker.getId()).map(WorkerDTO::new).orElse(null);
            return new ResponseEntity<>(workerDTO, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Worker not found", HttpStatus.NOT_FOUND);
        }
    }

    //Updates the worker associated with the given rut, if the worker exists, updates.
    @Override
    public ResponseEntity<Object> updateWorker(String rut, WorkerDTO workerDTO, String companyRut) {
        Worker worker = workerRepository.findWorkerByRut(rut);
        if (worker != null) {

            worker.setRut(workerDTO.getRut());
            worker.setFirstName(workerDTO.getFirstName());
            worker.setLastName(workerDTO.getLastName());
            worker.setSecondLastName(workerDTO.getSecondLastName());
            worker.setAddress(workerDTO.getAddress());
            if (companyRepository.existsCompanyByRut(companyRut)) {
                Company company = companyRepository.findCompanyByRut(companyRut);
                worker.setCompany(company);
            }
            workerRepository.save(worker);
            return new ResponseEntity<>("Worker updated! " + worker, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Worker not found", HttpStatus.NOT_FOUND);
        }
    }

    //Deletes a worker by its RUT if repository can find it in DB.
    @Override
    public ResponseEntity<Object> deleteWorker(String rut) {
        Worker worker = workerRepository.findWorkerByRut(rut);
        if (worker != null) {
            workerRepository.deleteById(worker.getId());
            return new ResponseEntity<>("Worker deleted", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Worker not found", HttpStatus.NOT_FOUND);
        }
    }
}
