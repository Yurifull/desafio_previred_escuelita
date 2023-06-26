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
import com.fcid.previred.models.Trabajador;
import com.fcid.previred.services.EmpresaService;
import com.fcid.previred.services.TrabajadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class TrabajadorRest {

	@Autowired
	private TrabajadorService trabajadorServ;

	@Autowired
	private EmpresaService empresaServ;

	// REGISTRAR TRABAJADOR
	@PostMapping("/trabajadores")
	@Operation(summary = "Crear un trabajador")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Trabajador creado exitosamente"),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida"),
			@ApiResponse(responseCode = "404", description = "Empresa no encontrada"),
			@ApiResponse(responseCode = "409", description = "El trabajador ya existe") })
	public ResponseEntity<Trabajador> crearTrabajador(@Valid @RequestBody Trabajador trabajador, BindingResult result) {
		// Verificar si hay errores de validación en el objeto 'trabajador'
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		// Obtener la empresa asociada al trabajador por su ID
		Empresa empresa = empresaServ.findById(trabajador.getEmpresa().getId());

		// Verificar si la empresa existe
		if (empresa == null) {
			return ResponseEntity.notFound().build();
		}

		// Comprobar si el trabajador ya existe por su RUT
		Trabajador trabajadorExistente = trabajadorServ.findByRut(trabajador.getRut());

		// Verificar si el trabajador ya existe
		if (trabajadorExistente != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		// Asociar el trabajador con la empresa
		trabajador.setEmpresa(empresa);

		// Crear el trabajador en la base de datos
		Trabajador trb = trabajadorServ.creartrabajador(trabajador);

		try {
			// Construir la URL de ubicación del trabajador creado
			URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(trb.getId())
					.toUri();
			return ResponseEntity.created(location).body(trb);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	// OBTENER LISTA DE TRABAJADORES
	@GetMapping("/trabajadores")
	@Operation(summary = "Obtener todos los trabajadores")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de trabajadores obtenida exitosamente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<List<Trabajador>> getAllTrabajadores() {
		// Obtener todos los trabajadores de la base de datos
		List<Trabajador> trabajadores = trabajadorServ.findAll();

		// Devolver una respuesta exitosa (código 200) con la lista de trabajadores
		return ResponseEntity.ok(trabajadores);
	}

	// OBTENER TRABAJADOR POR ID
	@GetMapping("/trabajadores/{id}")
	@Operation(summary = "Obtener un trabajador por su ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Trabajador encontrado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Trabajador no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<Trabajador> getTrabajadorById(
			@Parameter(description = "ID del trabajador", example = "1") @PathVariable("id") Long id) {
		// Devuelve la respuesta con el trabajador encontrado mediante el ID proporcionado
		return ResponseEntity.ok(trabajadorServ.findById(id));
	}

	// ACTUALIZAR TRABAJADOR POR ID
	@PutMapping("/trabajadores/{id}")
	@Operation(summary = "Actualizar un trabajador por su ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Trabajador actualizado exitosamente"),
		@ApiResponse(responseCode = "400", description = "Solicitud inválida"),
		@ApiResponse(responseCode = "404", description = "Trabajador no encontrado"),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<Trabajador> updateTrabajador(
		@Parameter(description = "ID del trabajador", example = "1") @PathVariable("id") Long id,
		@RequestBody Trabajador trabajador) {
		// Actualizar el trabajador mediante el ID y los datos proporcionados
		trabajadorServ.actualizartrabajador(id, trabajador);
		
		// Obtener el trabajador actualizado
		Trabajador updatedTrabajador = trabajadorServ.findById(id);
		
		// Verificar si el trabajador fue encontrado
		if (updatedTrabajador != null) {
			// Devolver la respuesta con el trabajador actualizado
			return ResponseEntity.ok(updatedTrabajador);
		} else {
			// Devolver una respuesta de "no encontrado"
			return ResponseEntity.notFound().build();
		}
	}


	// BORRAR TRABAJADOR POR ID
	@DeleteMapping("/trabajadores/{id}")
	@Operation(summary = "Eliminar un trabajador por su ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Trabajador eliminado exitosamente"),
		@ApiResponse(responseCode = "404", description = "Trabajador no encontrado"),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<Void> deleteTrabajadorById(
		@Parameter(description = "ID del trabajador", example = "1") @PathVariable("id") Long id) {
		// Eliminar el trabajador mediante el ID proporcionado
		trabajadorServ.borrartrabajador(id);
		
		// Devolver una respuesta exitosa
		return ResponseEntity.ok().build();
	}


}
