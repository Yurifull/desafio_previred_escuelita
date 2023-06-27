package com.previred.web.repositories;

import com.previred.web.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByRut(String rut);

    Company findCompanyByRut (String rut);

}
