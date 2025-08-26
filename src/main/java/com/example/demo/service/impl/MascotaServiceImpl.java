package com.example.demo.service.impl;

import com.example.demo.dto.mascota.MascotaRequestDTO;
import com.example.demo.dto.mascota.MascotaResponseDTO;
import com.example.demo.dto.mascota.MascotaUpdateDTO;
import com.example.demo.model.Mascota;
import com.example.demo.model.Usuario;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.interfaces.MascotaService;

import java.util.List;
import java.util.stream.Collectors;

public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final UsuarioRepository usuarioRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository, UsuarioRepository usuarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.usuarioRepository = usuarioRepository;
    }

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
        Usuario propietario = usuarioRepository.findById(mascotaRequest.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado."));

        Mascota mascota = new Mascota();
        mascota.setNombre(mascotaRequest.getNombre());
        mascota.setRaza(mascotaRequest.getRaza());
        mascota.setEspecie(mascotaRequest.getEspecie());
        mascota.setEdad(mascotaRequest.getEdad());
        mascota.setSexo(mascotaRequest.getSexo());
        mascota.setPropietario(propietario);

        return mapToResponseDTO(mascotaRepository.save(mascota));
    }

    @Override
    public MascotaResponseDTO updateMascota(Long id, MascotaUpdateDTO mascotaUpdate) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + id));

        if (mascotaUpdate.getNombre() != null) mascota.setNombre(mascotaUpdate.getNombre());
        if (mascotaUpdate.getEspecie() != null) mascota.setEspecie(mascotaUpdate.getEspecie());
        if (mascotaUpdate.getRaza() != null) mascota.setRaza(mascotaUpdate.getRaza());
        if (mascotaUpdate.getEdad() != null) mascota.setEdad(mascotaUpdate.getEdad());
        if (mascotaUpdate.getSexo() != null) mascota.setSexo(mascotaUpdate.getSexo());
        if (mascotaUpdate.getDescripcion() != null) mascota.setDescripcion(mascotaUpdate.getDescripcion());

        return mapToResponseDTO(mascotaRepository.save(mascota));
    }

    @Override
    public void deleteMascota(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada con id: " + id);
        }
        mascotaRepository.deleteById(id);
    }

    @Override
    public List<MascotaResponseDTO> getMascotasByUsuarioId(Long usuarioId) {
        return mascotaRepository.findByPropietario(usuarioId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MascotaResponseDTO> getMascotasByUsuario(Long usuarioId) {
        return mascotaRepository.findByPropietario(usuarioId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Métodos auxiliares
    private MascotaResponseDTO mapToResponseDTO(Mascota mascota) {
        MascotaResponseDTO dto = new MascotaResponseDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setDescripcion(mascota.getDescripcion());
        dto.setRaza(mascota.getRaza());
        dto.setEdad(mascota.getEdad());
        dto.setSexo(mascota.getSexo());
        //  TODO -
        dto.setUsuario(mascota.getPropietario().getId());
        return dto;
    }
}
