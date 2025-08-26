package com.example.demo.dto.preferencias;

import lombok.Data;

@Data
public class PreferenciasResponseDTO {
    private Long id;
    private Long usuarioId;
    private String especiePreferida;
    private String razaPreferida;
    private Integer edadMin;
    private Integer edadMax;
    private String sexoPreferido;
    private String ciudad;
}
