package com.example.demo.dto.match;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatchRequestDTO {
    @NotNull
    @JsonAlias({"usuario1Id"}) // transitorio: aceptará "usuario1Id" pero se mapeará aquí
    private Long mascota1Id;

    @NotNull
    @JsonAlias({"usuario2Id"}) // transitorio: aceptará "usuario2Id" pero se mapeará aquí
    private Long mascota2Id;

    private Boolean esMutuo = Boolean.FALSE;

    @AssertTrue(message = "mascota1Id y mascota2Id deben ser distintos")
    private boolean isDistinctPair() {
        return mascota1Id != null && mascota2Id != null && !mascota1Id.equals(mascota2Id);
    }
}
