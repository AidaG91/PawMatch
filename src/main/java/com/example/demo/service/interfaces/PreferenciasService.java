package com.example.demo.service.interfaces;

import com.example.demo.dto.preferencias.PreferenciasRequestDTO;
import com.example.demo.dto.preferencias.PreferenciasResponseDTO;
import com.example.demo.dto.preferencias.PreferenciasUpdateDTO;

import java.util.List;

public interface PreferenciasService {

    List<PreferenciasResponseDTO> getAllPreferencias();

    PreferenciasResponseDTO getPreferenciasById(Long usuarioId);

    PreferenciasResponseDTO createPreferencias(PreferenciasRequestDTO preferenciasRequest);

    PreferenciasResponseDTO updatePreferencias(Long id, PreferenciasUpdateDTO preferenciasUpdate);

    void deletePreferencias(Long id);
}
