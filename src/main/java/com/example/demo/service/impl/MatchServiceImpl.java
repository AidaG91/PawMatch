package com.example.demo.service.impl;

import com.example.demo.dto.match.MatchRequestDTO;
import com.example.demo.dto.match.MatchResponseDTO;
import com.example.demo.model.Mascota;
import com.example.demo.model.Match;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.repository.MatchRepository;
import com.example.demo.service.interfaces.MatchService;

import java.util.List;
import java.util.stream.Collectors;

public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final MascotaRepository mascotaRepository;

    public MatchServiceImpl(MatchRepository matchRepository, MascotaRepository mascotaRepository) {
        this.matchRepository = matchRepository;
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public List<MatchResponseDTO> getAllMatches() {
        return matchRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MatchResponseDTO getMatchById(Long id) {
       Match match = matchRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Match no encontrado con id: " + id));
       return mapToResponseDTO(match);
    }

    @Override
    public MatchResponseDTO createMatch(MatchRequestDTO matchRequest) {
        Mascota mascota1 = mascotaRepository.findById(matchRequest.getMascota1Id())
                .orElseThrow(() -> new RuntimeException("Mascota 1 no encontrada."));
        Mascota mascota2 = mascotaRepository.findById(matchRequest.getMascota2Id())
                .orElseThrow(() -> new RuntimeException("Mascota 2 no encontrada."));

        Match match = new Match();
        match.setMascotaOrigen(mascota1);
        match.setMascotaDestino(mascota2);

        return mapToResponseDTO(matchRepository.save(match));
    }

    @Override
    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match no encontrado con id: " + id);
        }
        matchRepository.deleteById(id);
    }

    @Override
    public List<MatchResponseDTO> getMatchesByUsuario(Long usuarioId) {
        return List.of();
    }

    // Métodos auxiliares
    private MatchResponseDTO mapToResponseDTO(Match match) {
        MatchResponseDTO dto = new MatchResponseDTO();
        dto.setId(match.getId());
        dto.setUsuario1Id(match.getMascotaOrigen().getPropietario().getId());
        dto.setUsuario2Id(match.getMascotaOrigen().getPropietario().getId());
        dto.setEsMutuo(match.getEsMutuo());
        return dto;
    }
}
