package com.previred.desafio.controllers;

import com.previred.desafio.entities.EmpleadoEntity;
import com.previred.desafio.repositories.EmpleadoRepository;
import com.previred.desafio.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService; // Dependencia del servicio de Empleado
    private final EmpleadoRepository empleadoRepository; // Dependencia del repositorio de Empleado

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService, EmpleadoRepository empleadoRepository) {
        this.empleadoService = empleadoService; // Asigna la instancia del servicio al campo empleadoService

        this.empleadoRepository = empleadoRepository; // Asigna la instancia del repositorio al campo empleadoRepository

    }

    /**
     * Crea un nuevo empleado.
     *
     * @param empleado El objeto que contiene la información del
     *                 empleado a crear.
     * @return Retorna el objeto creado.
     */
    @PostMapping("/crear")
    public ResponseEntity<EmpleadoEntity> crearEmpleado(@RequestBody EmpleadoEntity empleado) {
        EmpleadoEntity nuevoEmpleado = empleadoService.insertarEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

    /**
     * Obtiene la lista de todos los empleados.
     *
     * @return Retorna el array que contiene los empleados.
     */
    @GetMapping("/listar")
    public ResponseEntity<ArrayList<EmpleadoEntity>> listar() {
        ArrayList<EmpleadoEntity> empleados = (ArrayList<EmpleadoEntity>) empleadoRepository.findAll();
        return ResponseEntity.ok().body(empleados);
    }

    /**
     * Obtiene un empleado por su ID.
     *
     * @param id El ID del empleado a obtener.
     * @return Retorna el empleado encontrado si este existe.
     * 
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoEntity> getById(@PathVariable("id") Long id) {
        Optional<EmpleadoEntity> empleadoOptional = empleadoRepository.findById(id);

        if (empleadoOptional.isPresent()) {
            EmpleadoEntity empleado = empleadoOptional.get();
            return ResponseEntity.ok().body(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualiza la información de un empleado existente.
     *
     * @param id       ID del empleado a actualizar.
     * @param empleado objeto que contiene la información del empleado.
     * 
     * @return retorna el objeto actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoEntity> actualizarEmpleado(@PathVariable("id") Long id,
            @RequestBody EmpleadoEntity empleado) {
        Optional<EmpleadoEntity> empleadoOptional = empleadoRepository.findById(id);

        if (empleadoOptional.isPresent()) {
            EmpleadoEntity empleadoExistente = empleadoOptional.get();
            empleadoExistente.setRut(empleado.getRut());
            empleadoExistente.setNombre(empleado.getNombre());
            empleadoExistente.setApellidoP(empleado.getApellidoP());
            empleadoExistente.setApellidoM(empleado.getApellidoM());
            empleadoExistente.setDireccion(empleado.getDireccion());
            empleadoExistente.setIdEmpresa(empleado.getIdEmpresa());

            EmpleadoEntity empleadoActualizado = empleadoRepository.save(empleadoExistente);
            return ResponseEntity.ok().body(empleadoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un empleado por su ID.
     *
     * @param id ID del empleado a eliminar.
     * @return retorna el estado OK si fue eliminado correctamente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable("id") Long id) {
        Optional<EmpleadoEntity> empleadoOptional = empleadoRepository.findById(id);

        if (empleadoOptional.isPresent()) {
            empleadoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
