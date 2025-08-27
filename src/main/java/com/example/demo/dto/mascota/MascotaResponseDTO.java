package com.example.demo.dto.mascota;

import com.example.demo.enums.Sexo;
import com.example.demo.model.Usuario;
import lombok.Data;

@Data
public class MascotaResponseDTO {

    private Long id;

    private String nombre;

    private String raza;

    private String especie;

    private Integer edad;

    private Sexo sexo;

    private String descripcion;
// todo añadir más
   // private Long usuario;
    private Usuario propietario;
}
