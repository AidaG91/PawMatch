package com.example.demo.dto.preferencias;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PreferenciasRequestDTO {
    @NotNull
    private Long usuarioId;

    private String especiePreferida;
    private String razaPreferida;
    private Integer edadMin;
    private Integer edadMax;
    private String sexoPreferido;
    private String ciudad;
}
