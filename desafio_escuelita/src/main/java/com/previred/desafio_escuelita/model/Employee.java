package com.previred.desafio_escuelita.model;

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
 * The Employee class is a Java entity class that represents an employee with various attributes such
 * as employeeId, rut, name, surname, secondSurname, and address.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "employee")
public class Employee {

    // The `@Id` annotation is used to indicate that the `employeeId` field is the primary key of the
    // `Employee` entity.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    // The `@Column(name = "company_id")` annotation is used to specify the mapping of the `companyId`
    // field to a column in the database table. In this case, it indicates that the `companyId` field
    // should be mapped to a column named "company_id" in the database table. This annotation is part
    // of the Java Persistence API (JPA) and is used to define the mapping between Java objects and
    // database tables.
    @Column(name = "company_id")
    private Integer companyId;

    // The `@Column(name = "rut")` annotation is used to specify the mapping of the `rut` field to a
    // column in the database table. In this case, it indicates that the `rut` field should be mapped
    // to a column named "rut" in the database table. This annotation is part of the Java Persistence
    // API (JPA) and is used to define the mapping between Java objects and database tables.
    @Column(name = "rut")
    private Integer rut;

    // The `@Column(name = "name")` annotation is used to specify the mapping of the `name` field to a
    // column in the database table. In this case, it indicates that the `name` field should be mapped
    // to a column named "name" in the database table. This annotation is part of the Java Persistence
    // API (JPA) and is used to define the mapping between Java objects and database tables.
    @Column(name = "name")
    private String name;

    // The `@Column(name = "surname")` annotation is used to specify the mapping of the `surname` field
    // to a column in the database table. In this case, it indicates that the `surname` field should be
    // mapped to a column named "surname" in the database table. This annotation is part of the Java
    // Persistence API (JPA) and is used to define the mapping between Java objects and database
    // tables.
    @Column(name = "surname")
    private String surname;

    // The `@Column(name = "second_surname")` annotation is used to specify the mapping of the
    // `secondSurname` field to a column in the database table. In this case, it indicates that the
    // `secondSurname` field should be mapped to a column named "second_surname" in the database table.
    @Column(name = "second_surname")
    private String secondSurname;

    // The `@Column(name = "address")` annotation is used to specify the mapping of the `address` field
    // to a column in the database table. In this case, it indicates that the `address` field should be
    // mapped to a column named "address" in the database table.
    @Column(name = "address")
    private String address;
}
