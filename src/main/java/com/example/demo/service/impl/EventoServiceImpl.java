package com.example.demo.service.impl;

import com.example.demo.dto.evento.EventoRequestDTO;
import com.example.demo.dto.evento.EventoResponseDTO;
import com.example.demo.dto.evento.EventoUpdateDTO;
import com.example.demo.model.Evento;
import com.example.demo.model.Usuario;
import com.example.demo.repository.EventoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.interfaces.EventoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public List<EventoResponseDTO> getAllEventos() {
        return eventoRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EventoResponseDTO getEventoById(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
        return mapToResponseDTO(evento);
    }

    @Override
    public EventoResponseDTO createEvento(EventoRequestDTO eventoRequest) {
        Usuario creador = usuarioRepository.findById(eventoRequest.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario creador no encontrado"));

        Evento evento = new Evento();
        evento.setNombre(eventoRequest.getNombre());
        evento.setDescripcion(eventoRequest.getDescripcion());
        evento.setUbicacion(eventoRequest.getUbicacion());
        evento.setFechaEvento(eventoRequest.getFechaEvento());

        return mapToResponseDTO(eventoRepository.save(evento));
    }

    @Override
    public EventoResponseDTO updateEvento(Long id, EventoUpdateDTO eventoUpdate) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));

        if(eventoUpdate.getNombre() != null) evento.setNombre(eventoUpdate.getNombre());
        if(eventoUpdate.getDescripcion() != null) evento.setDescripcion(eventoUpdate.getDescripcion());
        if(eventoUpdate.getUbicacion() != null) evento.setUbicacion(eventoUpdate.getUbicacion());
        if(eventoUpdate.getFechaEvento() != null) evento.setFechaEvento(eventoUpdate.getFechaEvento());

        return mapToResponseDTO(eventoRepository.save(evento));

    }

    @Override
    public void deleteEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento no encontrado con id: " + id);
        }
        eventoRepository.deleteById(id);
    }

    // Métodos auxiliares
    private EventoResponseDTO mapToResponseDTO(Evento evento) {
        EventoResponseDTO dto = new EventoResponseDTO();
        dto.setId(evento.getId());
        dto.setNombre(evento.getNombre());
        dto.setDescripcion(evento.getDescripcion());
        dto.setUbicacion(evento.getUbicacion());
        dto.setFechaEvento(evento.getFechaEvento());
        return dto;
        }
}
