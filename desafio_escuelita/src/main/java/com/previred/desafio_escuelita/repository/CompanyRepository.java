package com.previred.desafio_escuelita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.previred.desafio_escuelita.model.Company;

// This code is defining an interface called `CompanyRepository` that extends the `JpaRepository`
// interface.
public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
