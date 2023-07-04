package com.previred.desafio.script.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.previred.desafio.fuentes.Empresa;
import com.previred.desafio.script.service.EmpresaService;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaREST {
	
	@Autowired
	private EmpresaService empresaService;
	/*Registrar una empresa*/
	@PostMapping
	private ResponseEntity<Empresa> guardar (@RequestBody Empresa empresa){
		Empresa temporal = empresaService.create(empresa);
		
		try 
		{
			return ResponseEntity.created(new URI("/api/empresa"+temporal.getId())).body(temporal);
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@GetMapping
	private ResponseEntity<List <Empresa>> listarTodasLasEmpresas (){
		
		return ResponseEntity.ok(empresaService.getAllEmpresas());
	}
	@DeleteMapping
	private ResponseEntity<Void> eliminarEmpresa (@RequestBody Empresa empresa){
		empresaService.delete(empresa);
		return ResponseEntity.ok().build();
	}
	@RequestMapping (value = "{id}", method=RequestMethod.GET)
	private ResponseEntity<Optional<Empresa>> listarEmpresasPorId (@PathVariable ("id")Long id){
		return ResponseEntity.ok(empresaService.findById(id));
	}
}
