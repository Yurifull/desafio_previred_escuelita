package com.previred.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.previred.app.entity.Empresa;
import com.previred.app.repository.EmpresaRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaRestController {
	@Autowired
    private  EmpresaRepository empresaRepository;
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtenerEmpresa(@PathVariable("id") Long id) {
        Empresa getEmpresa = empresaRepository.findById(id).orElse(null);

        if (getEmpresa != null) {
            return new ResponseEntity<>(getEmpresa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa input) {
        Empresa empresaExistente = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada."));

        // Entidades de la empresa
        empresaExistente.setRut(input.getRut());
        empresaExistente.setRazonSocial(input.getRazonSocial());
        empresaExistente.setFecha(input.getFecha());

        empresaRepository.save(empresaExistente);

        return ResponseEntity.ok("Empresa actualizada.");
    }
    
    @PostMapping
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa body) {
    	
		Empresa save =empresaRepository.save(body);
        return ResponseEntity.ok(save);
    }
   
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpresa(@PathVariable("id") Long id) {
    	//validacion si encuentra o no la empresa para el metodo eliminar
        if (empresaRepository.existsById(id)) {
        	empresaRepository.deleteById(id);
            return new ResponseEntity<>("Empresa eliminada.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empresa no encontrada.", HttpStatus.NOT_FOUND);
        }
    }
    
}
