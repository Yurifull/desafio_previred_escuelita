package com.previred.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "companies")
@Schema(description = "Class that represent a company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @NonNull
    @Schema(description = "Company's RUT", example = "5234567891")
    private String rut;

    @NonNull
    @Schema(description = "Company's name", example = "Perived Co.")
    private String companyName;

    @NonNull
    @Schema(description = "Creation date", example = "2002-03-24")
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(hidden = true)
    private List<Worker> workers;

    @Override
    public String toString() {
        return "Company{" +
                "rut='" + rut + '\'' +
                ", companyName='" + companyName + '\'' +
                ", date=" + date +
                '}';
    }
}
