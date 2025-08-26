package com.example.demo.dto.evento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotNull
    @Future
    private String ubicacion;

    @NotBlank(message = "La fecha no puede estar vacía.")
    private LocalDateTime fechaEvento;

    @NotNull
    private Long usuarioId;
}
