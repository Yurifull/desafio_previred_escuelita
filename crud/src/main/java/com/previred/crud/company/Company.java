package com.previred.crud.company;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//Creación de la entidad empresas

@Entity
@Table(name="companies")
public class Company{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="rut", unique = true)
	private String rut;
	
	@Column(name="business_name")
	private String business_name;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="record_insertion_date")
	private Date record_insertion_date;
	

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

	

	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	public Date getRecord_insertion_date() {
		return record_insertion_date;
	}

	public void setRecord_insertion_date(Date record_insertion_date) {
		this.record_insertion_date = record_insertion_date;
	}
	
	

	
	
	
}