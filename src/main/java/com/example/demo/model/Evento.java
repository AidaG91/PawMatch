package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "eventos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del evento es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    @Column(length = 255)
    private String descripcion;

    @NotBlank(message = "La ubicación es obligatoria")
    @Size(max = 150, message = "La ubicación no puede superar los 150 caracteres")
    @Column(nullable = false, length = 150)
    private String ubicacion;

    @NotNull(message = "La fecha del evento es obligatoria.")
    @Future(message = "La fecha del evento debe ser en el futuro.") // Hace que no permita una fecha del pasado
    @Column(nullable = false)
    private LocalDateTime fechaEvento;

    @CreationTimestamp // Fecha actual
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @ManyToMany
    @JoinTable(
            name = "evento_mascota",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "mascota_id")
    )
    private Set<Mascota> mascotasParticipantes = new HashSet<>();

 }
