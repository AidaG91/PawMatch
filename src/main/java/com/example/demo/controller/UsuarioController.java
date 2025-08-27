package com.example.demo.controller;

import com.example.demo.dto.usuario.UsuarioRequestDTO;
import com.example.demo.dto.usuario.UsuarioResponseDTO;
import com.example.demo.dto.usuario.UsuarioUpdateDTO;
import com.example.demo.service.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios(){
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
        /*
        Peticiones que vamos a ver:
        - GET: va sin cuerpo (body).
        - POST: Publicación de información. Va con cuerpo.
        - PUT: Sustituir un registro entero. Tengo un usuario, voy a sobreescribir el usuario entero.
        - PATCH: Cambio solo en una parte (un parche).
        - DELETE: Borrar. Tengo un usuario y lo quiero borrar.

        Esto se lee: Cuando alguien te haga una petición de tipo GET, y lo haga a la ruta arriba (linea 14), vas a
        ejecutar esta función.
        Esta función ejecuta el código getAllUsuarios.
        ResponseEntity = es un wrapper (una envoltura). Cuando mando datos a través de internet, quiero que esos
        datos estén empaquetados de una forma determinada.
         */
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@RequestBody UsuarioRequestDTO usuarioRequest){
        return ResponseEntity.ok(usuarioService.createUsuario(usuarioRequest));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(@PathVariable Long id,
                                                            @RequestBody UsuarioUpdateDTO usuarioUpdate) {
        return ResponseEntity.ok(usuarioService.updateUsuario(id, usuarioUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
