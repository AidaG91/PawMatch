package com.example.demo.dto.mensaje;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MensajeRequestDTO {

    @NotNull
    private Long chatId;

    @NotNull
    private Long remitenteId;

    @NotBlank
    private String contenido;

}
