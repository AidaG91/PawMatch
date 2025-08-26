package com.example.demo.dto.match;

import lombok.Data;

@Data
public class MatchResponseDTO {

    private Long id;
    private Long usuario1Id;
    private Long usuario2Id;
    private Boolean esMutuo;


}
