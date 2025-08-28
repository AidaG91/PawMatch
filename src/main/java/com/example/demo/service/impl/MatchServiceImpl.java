package com.example.demo.service.impl;

import com.example.demo.dto.match.MatchRequestDTO;
import com.example.demo.dto.match.MatchResponseDTO;
import com.example.demo.model.Mascota;
import com.example.demo.model.Match;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.repository.MatchRepository;
import com.example.demo.service.interfaces.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

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
        Mascota mascotaOrigen = mascotaRepository.findById(matchRequest.getMascotaOrigenId())
                .orElseThrow(() -> new RuntimeException("Mascota Origen no encontrada."));
        Mascota mascotaDestino = mascotaRepository.findById(matchRequest.getMascotaDestinoId())
                .orElseThrow(() -> new RuntimeException("Mascota Destino no encontrada."));

        Match match = new Match();
        match.setMascotaOrigen(mascotaOrigen);
        match.setMascotaDestino(mascotaDestino);

        return mapToResponseDTO(matchRepository.save(match));
    }

    @Override
    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match no encontrado con id: " + id);
        }
        matchRepository.deleteById(id);
    }

//    @Override
//    public List<MatchResponseDTO> getMatchesByMascotaId(Long id) {
//        return matchRepository.getMatchesByMascotaId(id)
//                .stream()
//                .map(this::mapToResponseDTO)
//                .collect(Collectors.toList());
//    }

    // Métodos auxiliares
    private MatchResponseDTO mapToResponseDTO(Match match) {
        MatchResponseDTO dto = new MatchResponseDTO();
        dto.setId(match.getId());
        dto.setMascotaOrigenId(match.getMascotaOrigen().getId());
        dto.setMascotaDestinoId(match.getMascotaDestino().getId());
        dto.setEstadoMatch(match.getEstado());
        dto.setFechaMatch(match.getFechaMatch());
            return dto;
    }
}
