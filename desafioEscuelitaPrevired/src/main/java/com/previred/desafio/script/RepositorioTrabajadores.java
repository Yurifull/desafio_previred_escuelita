package com.previred.desafio.script;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.previred.desafio.fuentes.Trabajadores;
@Repository
public interface RepositorioTrabajadores extends JpaRepository< Trabajadores , Long>{

}
