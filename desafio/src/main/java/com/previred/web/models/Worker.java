package com.previred.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "workers")
@Schema(description = "Class that represent a worker")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @NonNull
    @Schema(description = "Worker's RUT", example = "123456789")
    private String rut;

    @NonNull
    @Schema(description = "First name", example = "Camilo")
    private String firstName;

    @NonNull
    @Schema(description = "Last name", example = "Quezada")
    private String lastName;

    @NonNull
    @Schema(description = "Second last name", example = "Urrutia")
    private String secondLastName;

    @NonNull
    @Schema(description = "Address", example = "Balmaceda 1504, Rancagua")
    private String address;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    public Worker(@NonNull String rut,@NonNull String firstName,@NonNull String lastName,@NonNull String secondLastName,@NonNull String address, Company company) {
        this.rut = rut;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.address = address;
        this.company = company;
    }


    @Override
    public String toString() {
        return "Worker{" +
                "rut='" + rut + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
