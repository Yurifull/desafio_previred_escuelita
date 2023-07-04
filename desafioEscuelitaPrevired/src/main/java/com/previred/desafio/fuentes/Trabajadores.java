package com.previred.desafio.fuentes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "Trabajadores de la Empresa"
		+ "id, rut, apellido paterno, apellido materno y dirección física")
public class Trabajadores extends Empresa{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; /*variable que representa el id del trabajador*/

	private String rut; /*variable que representa el rut del trabajador*/

	private String nombre;/*variable que representa el nombre del trabajador*/

	private String apellidoPaterno;/*variable que representa el apellido paterno del trabajador*/

	private String apellidoMaterno;/*variable que representa el apellido materno del trabajador*/

	private String direccion;/*variable que representa la dirección del trabajador*/
	
	/*Definición de lal objeto empresa, perteneciente a la clase Empresa y utilización de la anotación @JoinColumn
	 * para juntar dos columnas"*/
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "empresa_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Empresa empresa;
	
	public Trabajadores() {
		
	}
	public Long getId() {
		return id;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getRut() {
		return rut;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Trabajadores(String rut, String nombre,
			String apellidoPaterno, String apellidoMaterno,
			String direccion, Empresa empresa) {
		this.rut = rut;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.direccion = direccion;
		this.empresa = empresa;
	}
}
