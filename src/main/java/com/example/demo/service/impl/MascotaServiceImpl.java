package com.example.demo.service.impl;

import com.example.demo.dto.mascota.MascotaRequestDTO;
import com.example.demo.dto.mascota.MascotaResponseDTO;
import com.example.demo.dto.mascota.MascotaUpdateDTO;
import com.example.demo.model.Mascota;
import com.example.demo.model.Usuario;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.interfaces.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaServiceImpl implements MascotaService {

   @Autowired
    MascotaRepository mascotaRepository;

   @Autowired
   UsuarioRepository usuarioRepository;

    @Override
    public List<MascotaResponseDTO> getAllMascotas() {
        return mascotaRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaResponseDTO getMascotaById(Long id) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + id));
        return mapToResponseDTO(mascota);
    }

    @Override
    public MascotaResponseDTO createMascota(MascotaRequestDTO mascotaRequest) {
        Mascota mascota = new Mascota();

        mascota.setNombre(mascotaRequest.getNombre());
        mascota.setRaza(mascotaRequest.getRaza());
        mascota.setEspecie(mascotaRequest.getEspecie());
        mascota.setEdad(mascotaRequest.getEdad());
        mascota.setSexo(mascotaRequest.getSexo());
        mascota.setDescripcion(mascotaRequest.getDescripcion());
// TODO AÑADIR MÁS

        Mascota savedMascota = mascotaRepository.save(mascota);
        return mapToResponseDTO(mascotaRepository.save(savedMascota));
    }

    @Override
    public MascotaResponseDTO updateMascota(Long id, MascotaUpdateDTO mascotaUpdate) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + id));

        if (mascotaUpdate.getNombre() != null) mascota.setNombre(mascotaUpdate.getNombre());
        if (mascotaUpdate.getEdad() != null) mascota.setEdad(mascotaUpdate.getEdad());
        if (mascotaUpdate.getDescripcion() != null) mascota.setDescripcion(mascotaUpdate.getDescripcion());

        Mascota updatedMascota = mascotaRepository.save(mascota);
        return mapToResponseDTO(updatedMascota);
    }

    @Override
    public void deleteMascota(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada con id: " + id);
        }
        mascotaRepository.deleteById(id);
    }

    @Override
    public List<MascotaResponseDTO> getMascotasByPropietarioId(Long propietarioId) {
        return mascotaRepository.findByPropietarioId(propietarioId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

//    public List<MascotaResponseDTO> getMascotasByUsuario(Long usuarioId) {
//        return mascotaRepository.findByPropietario(usuarioId)
//                .stream()
//                .map(this::mapToResponseDTO)
//                .collect(Collectors.toList());
//    }

    // Métodos auxiliares
    private MascotaResponseDTO mapToResponseDTO(Mascota mascota) {
        MascotaResponseDTO dto = new MascotaResponseDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setDescripcion(mascota.getDescripcion());
        dto.setRaza(mascota.getRaza());
        dto.setEdad(mascota.getEdad());
        dto.setSexo(mascota.getSexo());
        dto.setPropietarioId(mascota.getPropietario().getId());
        return dto;
    }
}
