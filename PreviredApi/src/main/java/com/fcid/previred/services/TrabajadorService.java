package com.fcid.previred.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcid.previred.models.Trabajador;
import com.fcid.previred.repositories.TrabajadorRepository;

@Service
public class TrabajadorService {

	@Autowired
	private TrabajadorRepository trabajadorRepo;

	public Trabajador creartrabajador(Trabajador trabajador) {
		return trabajadorRepo.save(trabajador);
	}

	public Trabajador findById(Long id) {
		return trabajadorRepo.findById(id).orElse(null);
	}

	public List<Trabajador> findAll() {
		return trabajadorRepo.findAll();
	}

	public void actualizartrabajador(Long id, Trabajador trabajador) {
		trabajadorRepo.findById(id).ifPresent(trb -> {
			trb.setRut(trabajador.getRut());
			trb.setNombre(trabajador.getNombre());
			trb.setApellidoPaterno(trabajador.getApellidoPaterno());
			trb.setApellidoMaterno(trabajador.getApellidoMaterno());
			trb.setEmpresa(trabajador.getEmpresa());
			trb.setDireccionFisica(trabajador.getDireccionFisica());
			trabajadorRepo.save(trb);
		});
	}

	public void borrartrabajador(Long id) {
		trabajadorRepo.deleteById(id);
	}

	public Trabajador findByRut(String rut) {
		return trabajadorRepo.findByRut(rut);
	}
}
