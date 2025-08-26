package com.example.demo.service.interfaces;

import com.example.demo.dto.evento.EventoRequestDTO;
import com.example.demo.dto.evento.EventoResponseDTO;
import com.example.demo.dto.evento.EventoUpdateDTO;

import java.util.List;

public interface EventoService {

    List<EventoResponseDTO> getAllEventos();

    EventoResponseDTO getEventoById(Long id);

    EventoResponseDTO createEvento(EventoRequestDTO eventoRequest);

    EventoResponseDTO updateEvento(Long id, EventoUpdateDTO eventoUpdate);

    void deleteEvento(Long id);
}
