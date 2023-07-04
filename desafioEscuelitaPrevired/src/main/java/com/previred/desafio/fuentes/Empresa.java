package com.previred.desafio.fuentes;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "Datos de la Empresa")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; /*la varible "id" funciona como un identificador para la empresa en la base de datos*/
	private String rut;
	
	/*Llamamos  a la lista de trabajadores registrados*/
	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<Trabajadores> trabajadores;
	
	private String razonSocial;
	
	@Column(updatable = false)/*Hacer que los campos de la columna de fecha de inserción no puedan ser modificados luego de establecerse*/
	@DateTimeFormat(pattern = "yyyy-MM-dd") /*Se establece una lógica de formato para las fechas, año-mes-día*/
	private Date fechaInsercion;
	

	public Empresa () {
		super();
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
	public Date getFechaInsercion() {
		return fechaInsercion;
	}
	public void setFechaInsercion(Date fechaInsercion) {
		this.fechaInsercion = fechaInsercion;
	}
	public void setTrabajadores(List<Trabajadores> trabajadores) {
		this.trabajadores = trabajadores;
		for (Trabajadores trabajadores2 : trabajadores)
		{
			trabajadores2.setEmpresa(this);
		}
	}
	public Empresa(Long id, String rut, String razonSocial,List<Trabajadores> trabajadores) {
		this.id = id;
		this.rut = rut;
		this.razonSocial = razonSocial;
		this.trabajadores = trabajadores;
	}

}

