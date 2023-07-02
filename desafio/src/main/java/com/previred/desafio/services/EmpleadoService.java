package com.previred.desafio.services;

import org.springframework.stereotype.Service;

import com.previred.desafio.entities.EmpleadoEntity;

import com.previred.desafio.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public EmpleadoEntity insertarEmpleado(EmpleadoEntity empleado) {
        return empleadoRepository.save(empleado);
    }
}
