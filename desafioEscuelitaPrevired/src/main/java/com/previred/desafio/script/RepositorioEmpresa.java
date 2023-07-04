package com.previred.desafio.script;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.previred.desafio.fuentes.Empresa;

@Repository
public interface RepositorioEmpresa extends JpaRepository< Empresa , Long>
{
	
}
