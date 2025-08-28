package com.example.demo.model;

import com.example.demo.enums.Caracter;
import com.example.demo.enums.Medida;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "preferencias")
@Builder

public class Preferencias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "La raza no puede tener más de 50 caracteres.")
    @Column(length = 50)
    private String raza;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Medida medida;

    @Min(value = 0, message = "La edad mínima no puede ser negativa.")
    @Max(value = 50, message = "La edad mínima no puede superar 50.")
    @Column(name = "edad_min")
    private Integer edadMin;

    @Min(value = 0, message = "La edad máxima no puede ser negativa.")
    @Max(value = 50, message = "La edad máxima no puede superar 50.")
    @Column(name = "edad_max")
    private Integer edadMax;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Caracter caracter;

    @Min(value = 1, message = "La distancia mínima es de 1 km.")
    @Max(value = 100, message = "La distancia máxima permitida es de 100 km. ")
    @Column(name = "distancia_max_km")
    private Integer distanciaMaxKm;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    @JsonBackReference
    private Usuario usuario;

}
