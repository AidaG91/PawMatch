package com.example.demo.util;

import com.example.demo.enums.Sexo;
import com.example.demo.model.Mascota;
import com.example.demo.model.Preferencias;
import com.example.demo.model.Usuario;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.repository.PreferenciasRepository;
import com.example.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    PreferenciasRepository preferenciasRepository;

    @Override
    public void run(String... args){

        // USUARIOS

        Usuario user1 = Usuario.builder()
                .nombre("Lucía García")
                .email("ejemplo@ejemplo.com")
                .password("1234545543")
                .ubicacion("Torremolinos")
                .telefono("6125478321")
                .build();

        Usuario user2 = Usuario.builder()
                .nombre("Carlos García")
                .email("carlos@ejemplo.com")
                .password("1458777442")
                .build();

//        usuarioRepository.save(user1);
//        usuarioRepository.save(user2);
        // Otra manera de guardar más directa:
        usuarioRepository.saveAll(List.of(user1, user2));

        // MASCOTAS
        Mascota mascota1 = Mascota.builder()
                .nombre("Lola")
                .edad(4)
                .sexo(Sexo.HEMBRA)
                .raza("Salchicha")
                .especie("Perro")
                .propietario(usuarioRepository.findById(1L).orElse(null))
                .build();

        mascotaRepository.saveAll(List.of(mascota1));

        // PREFERENCIAS
        Preferencias preferencias1 = Preferencias.builder()
                .usuario(usuarioRepository.findById(1L).orElse(null))
                .raza("Labrador")
                .edadMin(2)
                .build();

        preferenciasRepository.saveAll(List.of(preferencias1));
        System.out.println("+++++++++++++++++++++++++++++++++++ DATOS CARGADOS");
    }
}
