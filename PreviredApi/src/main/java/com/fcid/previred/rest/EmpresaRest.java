package com.fcid.previred.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fcid.previred.models.Empresa;
import com.fcid.previred.services.EmpresaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmpresaRest {

	@Autowired
	private EmpresaService empresaServ;

	// REGISTRAR UNA EMPRESA
	@PostMapping("/empresas")
	@Operation(summary = "Crear una empresa")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Empresa creada exitosamente"),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida"),
			@ApiResponse(responseCode = "409", description = "La empresa ya existe") })
	public ResponseEntity<Empresa> crearEmpresa(@Valid @RequestBody Empresa empresa, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		// Comprobar si la empresa ya ha sido registrada previamente
		Empresa empresaExistente = empresaServ.findByRazonSocial(empresa.getRazonSocial());
		if (empresaExistente != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		// Crear la empresa
		Empresa emp = empresaServ.crearEmpresa(empresa);

		try {
			URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(emp.getId())
					.toUri();
			return ResponseEntity.created(location).body(emp);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	// OBTENER LISTA DE EMPRESAS
	@GetMapping("/empresas")
	@Operation(summary = "Obtener todas las empresas")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<List<Empresa>> getAllEmpresas() {
		// Obtener todas las empresas
		List<Empresa> empresas = empresaServ.findAll();

		// Devolver una respuesta exitosa con la lista de empresas
		return ResponseEntity.ok(empresas);
	}

	// OBTENER EMPRESA POR ID
	@GetMapping("/empresas/{id}")
	@Operation(summary = "Obtener una empresa por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Empresa no encontrada") })
	public ResponseEntity<Empresa> getEmpresaById(
			@Parameter(description = "ID de la empresa", example = "1") @PathVariable("id") Long id) {
		// Obtener la empresa por su ID
		Empresa empresa = empresaServ.findById(id);

		// Verificar si la empresa existe
		if (empresa != null) {
			// Devolver una respuesta exitosa con la empresa encontrada
			return ResponseEntity.ok(empresa);
		} else {
			// Devolver una respuesta indicando que la empresa no fue encontrada
			return ResponseEntity.notFound().build();
		}
	}

	// ACTUALIZAR EMPRESA POR ID
	@PutMapping("/empresas/{id}")
	@Operation(summary = "Actualizar una empresa por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Empresa actualizada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Empresa no encontrada") })
	public ResponseEntity<Empresa> actualizarEmpresa(
			@Parameter(description = "ID de la empresa", example = "1") @PathVariable("id") Long id,
			@RequestBody Empresa empresa) {
		// Actualizar la empresa con los datos proporcionados
		empresaServ.actualizarEmpresa(id, empresa);

		// Obtener la empresa actualizada
		Empresa empresaActualizada = empresaServ.findById(id);

		// Verificar si la empresa existe
		if (empresaActualizada != null) {
			// Devolver una respuesta exitosa con la empresa actualizada
			return ResponseEntity.ok(empresaActualizada);
		} else {
			// Devolver una respuesta indicando que la empresa no fue encontrada
			return ResponseEntity.notFound().build();
		}
	}

	// BORRAR EMPRESA POR ID
	@DeleteMapping("/empresas/{id}")
	@Operation(summary = "Eliminar una empresa por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Empresa eliminada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Empresa no encontrada") })
	public ResponseEntity<Void> deleteEmpresaById(
			@Parameter(description = "ID de la empresa", example = "1") @PathVariable("id") Long id) {
		// Eliminar la empresa con el ID proporcionado
		empresaServ.borrarEmpresa(id);

		// Devolver una respuesta exitosa
		return ResponseEntity.ok().build();
	}

}
