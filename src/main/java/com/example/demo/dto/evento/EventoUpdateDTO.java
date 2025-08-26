package com.example.demo.dto.evento;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoUpdateDTO {

    private String nombre;

    private String descripcion;

    private String ubicacion;

    private LocalDateTime fechaEvento;
}
