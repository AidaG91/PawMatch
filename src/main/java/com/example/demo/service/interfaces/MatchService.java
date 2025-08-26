package com.example.demo.service.interfaces;

import com.example.demo.dto.match.MatchRequestDTO;
import com.example.demo.dto.match.MatchResponseDTO;

import java.util.List;

public interface MatchService {
    List<MatchResponseDTO> getAllMatches();

    MatchResponseDTO getMatchById(Long id);

    MatchResponseDTO createMatch(MatchRequestDTO matchRequest);

    void deleteMatch(Long id);

    List<MatchResponseDTO> getMatchesByUsuario(Long usuarioId);
}
