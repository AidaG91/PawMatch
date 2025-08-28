package com.example.demo.util;

import com.example.demo.enums.EstadoMatch;
import com.example.demo.enums.Sexo;
import com.example.demo.model.*;
import com.example.demo.repository.*;
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

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MatchRepository matchRepository;

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

        Mascota mascota2 = Mascota.builder()
                .nombre("Pepe")
                .edad(2)
                .sexo(Sexo.MACHO)
                .raza("Labrador")
                .especie("Perro")
                .propietario(usuarioRepository.findById(2L).orElse(null))
                .build();

        Mascota mascota3 = Mascota.builder()
                .nombre("Rex")
                .edad(5)
                .sexo(Sexo.MACHO)
                .raza("Pastor Alemán")
                .especie("Perro")
                .propietario(usuarioRepository.findById(2L).orElse(null))
                .build();

        mascotaRepository.saveAll(List.of(mascota1, mascota2, mascota3));

        // PREFERENCIAS
        Preferencias preferencias1 = Preferencias.builder()
                .usuario(usuarioRepository.findById(user1.getId()).orElse(null))
                .raza("Labrador")
                .edadMin(2)
                .build();

        preferenciasRepository.saveAll(List.of(preferencias1));

        // MATCH

        Match match1 = Match.builder()
                .mascotaOrigen(mascota1)
                .mascotaDestino(mascota2)
                .estado(EstadoMatch.CONFIRMADO)
                //.fechaMatch()
                .build();

        Match match2 = Match.builder()
                .mascotaOrigen(mascota1)
                .mascotaDestino(mascota3)
                .estado(EstadoMatch.PENDIENTE)
                .build();

        matchRepository.saveAll(List.of(match1, match2));

        // MENSAJE


        // CHAT
//        Chat chat1 = Chat.builder()
//                .usuario1(user1)
//                .usuario2(user2)
//               // .mensajes("Hola! Me gustan tus patitas, quieres quedar?")
//                .build();

//        chatRepository.saveAll(List.of(chat1));


        System.out.println("+++++++++++++++++++++++++++++++++++ DATOS CARGADOS");
    }
}
