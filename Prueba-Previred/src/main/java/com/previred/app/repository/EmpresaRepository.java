package com.previred.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.previred.app.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
}
