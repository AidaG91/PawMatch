package com.example.demo.dto.mascota;

import com.example.demo.enums.Sexo;
import lombok.Data;

@Data
public class MascotaUpdateDTO {

    private String nombre;

    private Integer edad;

    private String descripcion;

}
