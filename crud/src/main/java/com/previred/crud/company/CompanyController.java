package com.previred.crud.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
	private final CompanyService companyService;
	
	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
//Obtener el listado de todas las empresas
	
  @GetMapping 
  	public List<Company> getCompanies(){
	  return companyService.getCompanies();
  }
//Buscar una empresa mediante el id
  
  @GetMapping("/{id}")
  public Company getCompanyById(@PathVariable Long id) {
    return companyService.getCompanyById(id);
  }
  
 //Agregar una empresa
  @PostMapping
  	public ResponseEntity<Object> createCompany(@RequestBody Company company) {
	  return this.companyService.createCompany(company);
  }
  
 //Editar una empresa
  @PutMapping
	public ResponseEntity<Object> updateCompany(@RequestBody Company company) {
	  return this.companyService.updateCompany(company);
}
  
//Eliminar una empresa
  @DeleteMapping(path = "{companyId}")
  public ResponseEntity<Object> deleteCompany(@PathVariable("companyId") Long id){
	  return this.companyService.deleteCompany(id);
  }
}