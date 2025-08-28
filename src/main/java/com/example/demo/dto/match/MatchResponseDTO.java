package com.example.demo.dto.match;

import com.example.demo.enums.EstadoMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchResponseDTO {

    private Long id;

    @NotNull
    private Long mascotaOrigenId;

    @NotNull
    private Long mascotaDestinoId;

    @NotBlank
    private EstadoMatch estadoMatch;

    private LocalDateTime fechaMatch;
  }
