package com.example.demo.dto.preferencias;

import com.example.demo.enums.Caracter;
import com.example.demo.enums.Medida;
import com.example.demo.enums.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PreferenciasRequestDTO {

    @NotBlank
    private String raza;

    @NotBlank
    private Medida medida;

    @NotNull
    private Integer edadMin;

    @NotNull
    private Integer edadMax;

    @NotBlank
    private Caracter caracter;

    @NotBlank
    private Sexo genero;

    @NotNull
    private Integer distanciaMaxKm;
}
