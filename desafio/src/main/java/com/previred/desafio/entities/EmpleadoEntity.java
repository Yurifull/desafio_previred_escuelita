package com.previred.desafio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoEntity {
    @Id
    /**
     * Establecer secuencia de autoincremento del Id
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empleado_seq")
    @SequenceGenerator(name = "empleado_seq", sequenceName = "empleado_seq", allocationSize = 1)
    @Column(unique = true, nullable = false)
    private Long id;

    private String rut;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String direccion;

    @ManyToOne // relación muchos a uno
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity idEmpresa;

}
