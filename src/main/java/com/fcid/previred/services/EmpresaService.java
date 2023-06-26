package com.fcid.previred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcid.previred.models.Empresa;
import com.fcid.previred.repositories.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepo;

	public Empresa crearEmpresa(Empresa empresa) {
		return empresaRepo.save(empresa);
	}

	public Empresa findById(Long id) {
		return empresaRepo.findById(id).orElse(null);
	}

	public List<Empresa> findAll() {
		return empresaRepo.findAll();
	}

	public void actualizarEmpresa(Long id, Empresa empresa) {
		empresaRepo.findById(id).ifPresent(emp -> {
			emp.setRut(empresa.getRut());
			emp.setRazonSocial(empresa.getRazonSocial());
			empresaRepo.save(emp);
		});
	}

	public void borrarEmpresa(Long id) {
		empresaRepo.deleteById(id);
	}

	public Empresa findByRazonSocial(String razonSocial) {
		return empresaRepo.findByRazonSocialIgnoreCase(razonSocial);
	}
}
