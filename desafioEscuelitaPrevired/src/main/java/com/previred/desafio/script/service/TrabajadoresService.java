package com.previred.desafio.script.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.desafio.fuentes.Trabajadores;
import com.previred.desafio.script.RepositorioTrabajadores;

@Service
public class TrabajadoresService {
	
	@Autowired
	private RepositorioTrabajadores repositorioTrabajadores;
	
	public Trabajadores create (Trabajadores trabajadores) {
		return repositorioTrabajadores.save(trabajadores);
	}
	
	public List<Trabajadores> getAllTrabajadores (){
		return repositorioTrabajadores.findAll();
	}
	public void delete (Trabajadores trabajadores) {
		repositorioTrabajadores.delete(trabajadores);
	}
	public Optional<Trabajadores> findById (Long id) {
		return repositorioTrabajadores.findById(id);
	}
}
