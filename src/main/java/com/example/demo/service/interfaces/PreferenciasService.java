package com.example.demo.service.interfaces;

import com.example.demo.dto.preferencias.PreferenciasRequestDTO;
import com.example.demo.dto.preferencias.PreferenciasResponseDTO;
import com.example.demo.dto.preferencias.PreferenciasUpdateDTO;

public interface PreferenciasService {
    PreferenciasResponseDTO getPreferenciasByUsuarioId(Long usuarioId);

    PreferenciasResponseDTO createPreferencias(PreferenciasRequestDTO preferenciasRequest);

    PreferenciasResponseDTO updatePreferencias(Long id, PreferenciasUpdateDTO preferenciasUpdate);

    void deletePreferencias(Long id);
}
