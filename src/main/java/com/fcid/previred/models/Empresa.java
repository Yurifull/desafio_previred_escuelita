package com.fcid.previred.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "empresas")
@Schema(description = "Objeto que representa una empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo Rut es obligatorio")
    @Size(min = 8, max = 12, message = "El Rut debe tener entre 9 y 12 caracteres")
    @Pattern(regexp = "^\\d{7,8}-[\\dKk]$", message = "El Rut debe tener un formato válido")
    @Schema(description = "RUT de la empresa", example = "12345678-9")
    private String rut;

    @NotBlank(message = "El campo Razón Social es obligatorio")
    @Size(min = 2, max = 100, message = "La Razón Social debe tener entre 2 y 100 caracteres")
    @Schema(description = "Razón Social de la empresa", example = "Empresa ABC")
    private String razonSocial;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaDeInsercion;

	// Relaciones
	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<Trabajador> trabajadores;

	public Empresa() {
		super();
	}

	public Empresa(String rut, String razonSocial, List<Trabajador> trabajadores) {
		super();
		this.rut = rut;
		this.razonSocial = razonSocial;
		this.trabajadores = trabajadores;
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

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Date getfechaDeInsercion() {
		return fechaDeInsercion;
	}

	public void setfechaDeInsercion(Date fechaDeInsercion) {
		this.fechaDeInsercion = fechaDeInsercion;
	}

	public List<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
		for (Trabajador trabajador : trabajadores) {
			trabajador.setEmpresa(this);
		}
	}

	@PrePersist
	protected void onCreate() {
		this.fechaDeInsercion = new Date();
	}

}
