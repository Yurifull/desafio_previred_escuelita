package com.previred.crud.company;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	//Query opcional donde se buscara la empresa por el rut
	Optional<Company> findCompanyByRut(String rut);
	
}