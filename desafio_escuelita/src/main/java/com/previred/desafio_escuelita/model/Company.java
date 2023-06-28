package com.previred.desafio_escuelita.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The `Company` class is a Java entity class that represents a company and includes fields for the
 * company ID, RUT, mission, and registration date.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "company")
public class Company {

    // The `@Id` annotation is used to indicate that the `companyId` field is the primary key of the
    // `Company` entity.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    // The `@Column(name = "rut")` annotation is used to specify the mapping of the `rut` field to a
    // column in the database table. In this case, it indicates that the `rut` field should be mapped
    // to a column named "rut" in the "company" table.
    @Column(name = "rut")
    private Integer rut;

    // The `@Column(name = "mission")` annotation is used to specify the mapping of the `mission` field
    // to a column in the database table. In this case, it indicates that the `mission` field should be
    // mapped to a column named "mission" in the "company" table.
    @Column(name = "mission")
    private String mission;

    // The `@Column(name = "registration_date")` annotation is used to specify the mapping of the
    // `registrationDate` field to a column in the database table. In this case, it indicates that the
    // `registrationDate` field should be mapped to a column named "registration_date" in the "company"
    // table.
    @Column(name = "registration_date")
    private Date registrationDate;
}
