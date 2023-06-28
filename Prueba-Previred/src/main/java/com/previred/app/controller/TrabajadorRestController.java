package com.previred.app.controller;



import com.previred.app.entity.Trabajador;
import com.previred.app.repository.TrabajadorRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/api/trabajador")
public class TrabajadorRestController {
    @Autowired
    private TrabajadorRepository trabajadorRepository;
    @GetMapping("/{id}")
    public ResponseEntity<Trabajador> obtenerTrabajador(@PathVariable("id") Long id) {
        Trabajador getTrabajador = trabajadorRepository.findById(id).orElse(null);

        if (getTrabajador != null) {
            return new ResponseEntity<>(getTrabajador, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTrabajador(@PathVariable Long id, @RequestBody Trabajador input) {
    	Trabajador trabajadorExistente = trabajadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado."));

        // Entidades de la tabla trabajador
    	
    	trabajadorExistente.setRut(input.getRut());
    	trabajadorExistente.setApellidoPaterno(input.getApellidoPaterno());
    	trabajadorExistente.setApellidoMaterno(input.getApellidoMaterno());
    	trabajadorExistente.setDireccion(input.getDireccion());
        

        trabajadorRepository.save(trabajadorExistente);

        return ResponseEntity.ok("Trabajador actualizado.");
    }
    
    @PostMapping
    public ResponseEntity<Trabajador> crearTrabajador(@RequestBody Trabajador input) {
    	
    	Trabajador save =trabajadorRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTrabajador(@PathVariable("id") Long id) {
    	//validacion si encuentra o no la empresa para el metodo eliminar
        if (trabajadorRepository.existsById(id)) {
        	trabajadorRepository.deleteById(id);
            return new ResponseEntity<>("Trabajador eliminado.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Trabajador no encontrado.", HttpStatus.NOT_FOUND);
        }
    }
    
}