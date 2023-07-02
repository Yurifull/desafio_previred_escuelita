package com.previred.crud.company;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
	
	HashMap<String, Object> datos;
	
	private final CompanyRepository companyRepository;
//Se crea la función que obtendrá las dependencias de CompanyRepository
//Para ser inyectadas en el CompanyController	
	@Autowired
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
//Servicio para buscar el listado de empresas
	public List<Company> getCompanies(){
		return this.companyRepository.findAll();
	}
//Servicio para buscar una empresa por el Id
	public Company getCompanyById( Long Id) {
		return this.companyRepository.findById(Id).get();
	}
//Servicio para crear una empresa
	public ResponseEntity<Object> createCompany(Company company) {
		Optional<Company> res = companyRepository.findCompanyByRut(company.getRut());
		datos = new HashMap<>();
	//Se crea una condicion donde se buscara si existe el rut
	//Si existe un rut no se creara la empresa 
		if(res.isPresent()) {
			datos.put("error", true);
			datos.put("message", "El rut ingresado ya existe");
			return new ResponseEntity<>(
						datos,
						HttpStatus.CONFLICT
			);
		}
		companyRepository.save(company);
			datos.put("data", datos);
			datos.put("message", "La empresa se ha guardado con exito");
			return new ResponseEntity<>(
				company,
				HttpStatus.CREATED
					);
	}
//Servicio para editar una empresa
	public ResponseEntity<Object> updateCompany(Company company) {
		Optional<Company> res = companyRepository.findCompanyByRut(company.getRut());
		datos = new HashMap<>();
		
		if(res.isPresent()) {
			companyRepository.save(company);
			datos.put("data", datos);
			datos.put("message", "La empresa se ha guardado con exito");
			return new ResponseEntity<>(
				company,
				HttpStatus.CREATED
					);
		
		}else {
			datos.put("error", true);
			datos.put("message", "El rut ingresado no existe");
			return new ResponseEntity<>(
						datos,
						HttpStatus.CONFLICT
			);
		
		}	
	}
//Servicio para eliminar una empresa por el id
	public ResponseEntity<Object> deleteCompany(Long id) {
		datos = new HashMap<>();
		boolean exists =  this.companyRepository.existsById(id);
		if(!exists) {
			datos.put("error", true);
			datos.put("message", "la empresa no existe");
			return new ResponseEntity<>(
						datos,
						HttpStatus.CONFLICT
					);
		}
		companyRepository.deleteById(id);
		datos.put("message", "empresa eliminada");
		return new ResponseEntity<>(
					datos,
					HttpStatus.ACCEPTED
				);
	}
}