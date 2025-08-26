package com.example.demo.dto.chat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatRequestDTO {

    @NotNull
    private Long matchId;

    @NotNull
    private Long usuario1Id;

    @NotNull
    private Long usuario2Id;
}
