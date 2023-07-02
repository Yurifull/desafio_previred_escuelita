package com.previred.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.previred.desafio.entities.EmpleadoEntity;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {

}
