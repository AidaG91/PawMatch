package com.example.demo.service.impl;

import com.example.demo.dto.usuario.UsuarioRequestDTO;
import com.example.demo.dto.usuario.UsuarioResponseDTO;
import com.example.demo.dto.usuario.UsuarioUpdateDTO;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO getUsuarioById(Long id) {
       Usuario usuario = usuarioRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
       return mapToResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setPassword(usuarioRequest.getPassword());
        usuario.setCiudad(usuarioRequest.getCiudad());

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return mapToResponseDTO(savedUsuario);
    }

    @Override
    public UsuarioResponseDTO updateUsuario(Long id, UsuarioUpdateDTO usuarioUpdate) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        if (usuarioUpdate.getNombre() != null) usuario.setNombre(usuarioUpdate.getNombre());
        if (usuarioUpdate.getEmail() != null) usuario.setEmail(usuarioUpdate.getEmail());
        if (usuarioUpdate.getPassword() != null) usuario.setPassword(usuarioUpdate.getPassword());
        if (usuarioUpdate.getCiudad() != null) usuario.setCiudad(usuarioUpdate.getCiudad());

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return mapToResponseDTO(updatedUsuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Métodos auxiliares
    private UsuarioResponseDTO mapToResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setCiudad(usuario.getCiudad());
        return dto;
       }
}
