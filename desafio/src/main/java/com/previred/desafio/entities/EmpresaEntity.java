package com.previred.desafio.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaEntity {
    @Id
    /**
     * Establecer secuencia de autoincremento del Id
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_seq")
    @SequenceGenerator(name = "empresa_seq", sequenceName = "empresa_seq", allocationSize = 1)
    @Column(unique = true, nullable = false)
    private Long id;

    private String rut;
    private String nombre;
    private String razonSocial;
    private String fechaRegistro;

    /**
     * Captura la fecha al momento de crearse el objeto
     * 
     * @PrePersist - antes de la persistencia de datos
     */
    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDate.now().toString();
    }
}
