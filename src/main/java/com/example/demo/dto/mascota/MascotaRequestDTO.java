package com.example.demo.dto.mascota;

import com.example.demo.enums.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MascotaRequestDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String raza;

    @NotBlank
    private String especie;

    @NotNull
    private Integer edad;

    @NotBlank
    private Sexo sexo;

    private String descripcion;

    private Long usuarioId;
}
