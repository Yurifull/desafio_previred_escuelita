package com.fcid.previred.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "trabajadores")
@Schema(description = "Objeto que representa un trabajador")
public class Trabajador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El campo Rut es obligatorio")
	@Size(min = 8, max = 12, message = "El Rut debe tener entre 9 y 12 caracteres")
	@Pattern(regexp = "^\\d{7,8}-[\\dKk]$", message = "El Rut debe tener un formato válido")
	@Schema(description = "RUT del trabajador", example = "12345678-9")
	private String rut;

	@NotBlank(message = "El campo Nombre es obligatorio")
	@Pattern(regexp = "^[A-Za-záéíóúüñÁÉÍÓÚÜÑ ]+$", message = "El campo Nombre solo puede contener letras y espacios")
	@Schema(description = "Nombre del trabajador", example = "Juan")
	private String nombre;

	@NotBlank(message = "El campo Apellido Paterno es obligatorio")
	@Pattern(regexp = "^[A-Za-záéíóúüñÁÉÍÓÚÜÑ ]+$", message = "El campo Nombre solo puede contener letras y espacios")
	@Schema(description = "Apellido paterno del trabajador", example = "Pérez")
	private String apellido_paterno;

	@NotBlank(message = "El campo Apellido Materno es obligatorio")
	@Pattern(regexp = "^[A-Za-záéíóúüñÁÉÍÓÚÜÑ ]+$", message = "El campo Nombre solo puede contener letras y espacios")
	@Schema(description = "Apellido materno del trabajador", example = "Gómez")
	private String apellido_materno;

	@NotBlank(message = "El campo Dirección Física es obligatorio")
	@Pattern(regexp = "^[A-Za-záéíóúüñÁÉÍÓÚÜÑ\\s]+,[\\s\\d]+,[A-Za-záéíóúüñÁÉÍÓÚÜÑ\\s]+$", message = "El campo Dirección Física debe tener el formato 'nombre_calle, numero_de_casa, nombre_comuna'")
	@Schema(description = "Dirección física del trabajador", example = "Calle 123, 456, Comuna A")
	private String direccion_fisica;

	// Relaciones
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "empresa_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Empresa empresa;

	public Trabajador() {
		super();
	}

	public Trabajador(String rut, String nombre, String apellido_paterno, String apellido_materno,
			String direccion_fisica, Empresa empresa) {
		super();
		this.rut = rut;
		this.nombre = nombre;
		this.apellido_paterno = apellido_paterno;
		this.apellido_materno = apellido_materno;
		this.direccion_fisica = direccion_fisica;
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellido_paterno;
	}

	public void setApellidoPaterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellidoMaterno() {
		return apellido_materno;
	}

	public void setApellidoMaterno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public String getDireccionFisica() {
		return direccion_fisica;
	}

	public void setDireccionFisica(String direccion_fisica) {
		this.direccion_fisica = direccion_fisica;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
