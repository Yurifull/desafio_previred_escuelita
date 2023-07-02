package com.previred.desafio.controllers;

import com.previred.desafio.entities.EmpresaEntity;
import com.previred.desafio.repositories.EmpresaRepository;
import com.previred.desafio.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    /**
     * @param empresaService Servicio de la empresa
     */
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService; // Asigna la instancia del servicio al campo empresaService
    }

    /**
     * Crear una nueva empresa.
     *
     * @param empresa Objeto de la empresa que se va a crear
     * @return Retorna el status CREATED
     */
    @PostMapping("/crear")
    public ResponseEntity<EmpresaEntity> crearEmpresa(@RequestBody EmpresaEntity empresa) {
        EmpresaEntity nuevaEmpresa = empresaService.insertarEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEmpresa);
    }

    @Autowired
    EmpresaRepository empresaRepository;

    /**
     * listar todas las empresas.
     *
     * @return Retorna el array con las empresas
     */
    @GetMapping("/listar")
    public ResponseEntity<ArrayList<EmpresaEntity>> listar() {
        ArrayList<EmpresaEntity> empresas = (ArrayList<EmpresaEntity>) empresaRepository.findAll();
        return ResponseEntity.ok().body(empresas);
    }

    /**
     * Obtener una empresa por su ID.
     *
     * @param id ID de la empresa a buscar
     * @return Retorna el objeto de la empresa encontrasa si esta existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaEntity> getById(@PathVariable("id") Long id) {
        Optional<EmpresaEntity> empresaOptional = empresaRepository.findById(id);

        if (empresaOptional.isPresent()) {
            EmpresaEntity empresa = empresaOptional.get();
            return ResponseEntity.ok().body(empresa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualizar una empresa existente.
     *
     * @param id      ID de la empresa a actualizar
     * @param empresa Objeto de la empresa
     * @return Retorna el objeto actualizado si fue editado exitosamente
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaEntity> actualizarEmpresa(@PathVariable("id") Long id,
            @RequestBody EmpresaEntity empresa) {
        Optional<EmpresaEntity> empresaOptional = empresaRepository.findById(id);

        if (empresaOptional.isPresent()) {
            EmpresaEntity empresaExistente = empresaOptional.get();
            empresaExistente.setRut(empresa.getRut());
            empresaExistente.setNombre(empresa.getNombre());
            empresaExistente.setRazonSocial(empresa.getRazonSocial());
            empresaExistente.setFechaRegistro(empresa.getFechaRegistro());

            EmpresaEntity empresaActualizada = empresaRepository.save(empresaExistente);
            return ResponseEntity.ok().body(empresaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * eliminar una empresa por su ID.
     *
     * @param id ID de la empresa a eliminar
     * @return retorna el estado OK si fue eliminado correctamente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable("id") Long id) {
        Optional<EmpresaEntity> empresaOptional = empresaRepository.findById(id);

        if (empresaOptional.isPresent()) {
            empresaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
