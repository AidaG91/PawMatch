package com.example.demo.dto.mensaje;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MensajeResponseDTO {

    private Long id;
    private Long chatId;
    private Long remitenteId;
    private String contenido;
    private LocalDateTime fechaEnvio;

}
