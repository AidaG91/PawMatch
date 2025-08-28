package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Builder

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.") //lanza un error diciendo que el nombre no puede estar en blanco
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
                                            // evitamos que el nombre sea más largo de 100 caracteres y salta un error.
    @Column(nullable = false, length = 100)
    private String nombre;

    private String ciudad;

    @NotBlank
    @Email(message = "El e-mail no tiene un formato válido.")
    @Size(max = 150, message = "El e-mail no puede tener más de 150 caracteres.")
    @Column(nullable = false, length = 150, unique = true)
    private String email;
    // a parte, para que el email sea único y no se repita hemos añadido arriba el "uniqueConstraints".

    @NotBlank
    @Size(max = 150, message = "La contraseña no puede tener más de 150 caracteres.")
    @Column(nullable = false, length = 150, unique = false)
    private String password;

    @Size(max = 150, message = "La ubicación no puede tener más de 150 caracteres.")
    @Column(nullable = true, length = 150, unique = false)
    private String ubicacion;

    @CreationTimestamp // Le estoy diciendo que lo que viene a continuación es una fecha.
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    private String telefono; // Los teléfonos siempre son String

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas = new ArrayList<>();

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Preferencias preferencias;
}
