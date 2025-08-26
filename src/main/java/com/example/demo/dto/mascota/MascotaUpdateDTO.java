package com.example.demo.dto.mascota;

import com.example.demo.enums.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MascotaUpdateDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String especie;

    private String raza;

    @NotNull
    private Integer edad;

    private Sexo sexo;

    private String descripcion;

}
