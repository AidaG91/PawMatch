package com.example.demo.service.interfaces;

import com.example.demo.dto.mascota.MascotaRequestDTO;
import com.example.demo.dto.mascota.MascotaResponseDTO;
import com.example.demo.dto.mascota.MascotaUpdateDTO;

import java.util.List;

public interface MascotaService {

    List<MascotaResponseDTO> getAllMascotas();

    MascotaResponseDTO getMascotaById(Long id);

    MascotaResponseDTO createMascota(MascotaRequestDTO mascotaRequest);

    MascotaResponseDTO updateMascota(Long id, MascotaUpdateDTO mascotaUpdate);

    void deleteMascota(Long id);

    List<MascotaResponseDTO> getMascotasByUsuarioId(Long usuarioId);
}
