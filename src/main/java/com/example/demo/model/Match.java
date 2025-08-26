package com.example.demo.model;
import com.example.demo.enums.EstadoMatch;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/*
¿Qué hacen @Table y @Builder?
@Table
Personaliza cómo se mapeará la entidad en la base de datos.
name = "matches" → especifica el nombre exacto de la tabla.
Si no lo pones, JPA tomaría el nombre de la clase (Match) en plural o tal cual.
uniqueConstraints → define restricciones de unicidad a nivel de tabla:
En este caso, asegura que no puedan existir dos filas con la misma combinación de mascota_origen_id y mascota_destino_id, evitando duplicados de matches.
Equivale a:
    CREATE TABLE matches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mascota_origen_id BIGINT NOT NULL,
    mascota_destino_id BIGINT NOT NULL,
    UNIQUE (mascota_origen_id, mascota_destino_id)
);


@Builder
Es de nuestro querido Lombok
Implementa el patrón Builder de forma automática. Básicamente nos permite crear una instancia con poco código.
Nos ahorra luego líneas de código, te dejo un código de ejemplo:
    Match nuevoMatch = Match.builder()
        .mascotaOrigen(mascota1)
        .mascotaDestino(mascota2)
        .estado(EstadoMatch.PENDIENTE)
        .build();

 Sin Builder el constructor quedaría así:
 Match nuevoMatch = new Match(null, mascota1, mascota2, EstadoMatch.PENDIENTE, LocalDateTime.now());
 */

@Entity
@Table(
        name = "matches",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"mascota_origen_id", "mascota_destino_id"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean esMutuo = false;

    /**
     * Mascota que inicia el "like"
     */
    @NotNull(message = "La mascota origen es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_origen_id", nullable = false)
    private Mascota mascotaOrigen;

    /**
     * Mascota que recibe el "like"
     */
    @NotNull(message = "La mascota destino es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_destino_id", nullable = false)
    private Mascota mascotaDestino;

    /**
     * Estado del match:
     * - PENDIENTE: Solo un lado ha dado "like"
     * - CONFIRMADO: Ambos lados dieron "like"
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EstadoMatch estado;

    @CreationTimestamp
    @Column(name = "fecha_match", nullable = false, updatable = false)
    private LocalDateTime fechaMatch;

    @AssertTrue(message = "Un match no puede ser consigo mismo")
    private boolean isParValido() {
        return mascotaOrigen != null && mascotaDestino != null &&
                !mascotaOrigen.getId().equals(mascotaDestino.getId());
    }

    @PrePersist @PreUpdate
    private void normalizarPar() {
        if (mascotaOrigen == null || mascotaDestino == null) return;
        Long id1 = mascotaOrigen.getId(), id2 = mascotaDestino.getId();
        if (id1 == null || id2 == null) throw new IllegalStateException("Mascotas deben estar persistidas");
        if (id1 > id2) { Mascota t = mascotaOrigen; mascotaOrigen = mascotaDestino; mascotaDestino = t; }
    }
}
