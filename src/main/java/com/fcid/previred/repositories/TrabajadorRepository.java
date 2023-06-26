package com.fcid.previred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fcid.previred.models.Trabajador;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long>{

	Trabajador findByRut(String rut);

}
