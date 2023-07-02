package com.previred.desafio.services;

import org.springframework.stereotype.Service;

import com.previred.desafio.entities.EmpresaEntity;
import com.previred.desafio.repositories.EmpresaRepository;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaEntity insertarEmpresa(EmpresaEntity empresa) {
        return empresaRepository.save(empresa);
    }
}
