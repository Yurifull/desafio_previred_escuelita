package com.previred.web.repositories;

import com.previred.web.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    boolean existsWorkerByRut(String rut);

    Worker findWorkerByRut(String rut);
}
