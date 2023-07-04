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
import com.previred.desafio.fuentes.Trabajadores;
import com.previred.desafio.script.service.TrabajadoresService;

@RestController
@RequestMapping("/api/trabajadores")
public class TrabajadoresREST {
	@Autowired
	private TrabajadoresService trabajadoresService;
	
	@PostMapping
	private ResponseEntity<Trabajadores> guardar (@RequestBody Trabajadores trabajadores){
		Trabajadores temporal = trabajadoresService.create(trabajadores);
		
		try 
		{
			return ResponseEntity.created(new URI("/api/trabajadores"+temporal.getId())).body(temporal);
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@GetMapping
	private ResponseEntity<List <Trabajadores>> listarTodosLosTrabajadores (){
		
		return ResponseEntity.ok(trabajadoresService.getAllTrabajadores());
	}
	@DeleteMapping
	private ResponseEntity<Void> eliminarTrabajadores (@RequestBody Trabajadores trabajadores){
		trabajadoresService.delete(trabajadores);
		return ResponseEntity.ok().build();
	}
	@RequestMapping (value = "{id}",method=RequestMethod.GET)
	private ResponseEntity <Optional<Trabajadores>> listarTrabajadoresPorId (@PathVariable ("id")Long id)
	{
		return ResponseEntity.ok(trabajadoresService.findById(id));
	}
}

