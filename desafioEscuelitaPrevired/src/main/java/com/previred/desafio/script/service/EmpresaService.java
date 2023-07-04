package com.previred.desafio.script.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.desafio.fuentes.Empresa;
import com.previred.desafio.script.RepositorioEmpresa;


@Service
public class EmpresaService {
	
	@Autowired
	private RepositorioEmpresa repositorioEmpresa;
	
	public Empresa create (Empresa empresa) {
		return repositorioEmpresa.save(empresa);
	}
	
	public List<Empresa> getAllEmpresas (){
		return repositorioEmpresa.findAll();
	}
	public void delete (Empresa empresa) {
		repositorioEmpresa.delete(empresa);
	}
	public Optional<Empresa> findById (Long id ) {
		return repositorioEmpresa.findById(id);
	}
	
}
