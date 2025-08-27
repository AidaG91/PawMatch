package com.example.demo.dto.preferencias;

import com.example.demo.enums.Caracter;
import com.example.demo.enums.Medida;
import com.example.demo.enums.Sexo;
import lombok.Data;

@Data
public class PreferenciasUpdateDTO {
    private String raza;
    private Medida medida;
    private Integer edadMin;
    private Integer edadMax;
    private Caracter caracter;
    private Sexo genero;
    private Integer distanciaMaxKm;
}
