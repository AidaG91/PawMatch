package com.example.demo.service.impl;

import com.example.demo.dto.preferencias.PreferenciasRequestDTO;
import com.example.demo.dto.preferencias.PreferenciasResponseDTO;
import com.example.demo.dto.preferencias.PreferenciasUpdateDTO;
import com.example.demo.model.Preferencias;
import com.example.demo.repository.PreferenciasRepository;
import com.example.demo.service.interfaces.PreferenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreferenciasServiceImpl implements PreferenciasService {

    @Autowired
    PreferenciasRepository preferenciasRepository;


    @Override
    public List<PreferenciasResponseDTO> getAllPreferencias() {
        return preferenciasRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PreferenciasResponseDTO getPreferenciasById(Long id) {
        Preferencias preferencias = preferenciasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Preferencias no encontradas con id: " + id));
        return (PreferenciasResponseDTO) mapToResponseDTO(preferencias);
    }

    @Override
    public PreferenciasResponseDTO createPreferencias(PreferenciasRequestDTO preferenciasRequest) {
        Preferencias preferencias = new Preferencias();

        Preferencias savedPreferencias = preferenciasRepository.save(preferencias);
        return (PreferenciasResponseDTO) mapToResponseDTO(savedPreferencias);
    }

    @Override
    public PreferenciasResponseDTO updatePreferencias(Long id, PreferenciasUpdateDTO preferenciasUpdate) {
        Preferencias preferencias = preferenciasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Preferencias no encontradas con el id: " + id));

        Preferencias updatedPreferencias = preferenciasRepository.save(preferencias);
        return (PreferenciasResponseDTO) mapToResponseDTO(updatedPreferencias);
    }

    @Override
    public void deletePreferencias(Long id) {
        if (!preferenciasRepository.existsById(id)) {
            throw new RuntimeException("Prefencias no encontradas con id: " + id);
        }
        preferenciasRepository.deleteById(id);
    }

    // Métodos auxiliares

    private PreferenciasResponseDTO mapToResponseDTO(Preferencias preferencias) {
        PreferenciasResponseDTO dto = new PreferenciasResponseDTO();
        dto.setId(preferencias.getId());
        dto.setCaracter(preferencias.getCaracter());
        dto.setEdadMin(preferencias.getEdadMin());
        dto.setEdadMax(preferencias.getEdadMax());
        dto.setMedida(preferencias.getMedida());
        dto.setDistanciaMaxKm(preferencias.getDistanciaMaxKm());
       // dto.setGenero(preferencias.);
        dto.setRaza(preferencias.getRaza());;
        dto.setUsuarioId(preferencias.getUsuario().getId());
        return dto;
    }

}
