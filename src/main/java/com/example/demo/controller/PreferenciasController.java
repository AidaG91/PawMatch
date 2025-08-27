package com.example.demo.controller;

import com.example.demo.dto.preferencias.PreferenciasRequestDTO;
import com.example.demo.dto.preferencias.PreferenciasResponseDTO;
import com.example.demo.dto.preferencias.PreferenciasUpdateDTO;
import com.example.demo.service.interfaces.PreferenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferencias")

public class PreferenciasController {
    @Autowired
    private PreferenciasService preferenciasService;

    @GetMapping
    public ResponseEntity<List<PreferenciasResponseDTO>> getAllPreferencias() {
        return ResponseEntity.ok(preferenciasService.getAllPreferencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreferenciasResponseDTO> getPreferenciasById(@PathVariable Long id) {
        return ResponseEntity.ok(preferenciasService.getPreferenciasById(id));
    }

    @PostMapping
    public ResponseEntity<PreferenciasResponseDTO> createPreferencias(@RequestBody PreferenciasRequestDTO preferenciasRequest) {
        return ResponseEntity.ok(preferenciasService.createPreferencias(preferenciasRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreferenciasResponseDTO> updatePreferencias(@PathVariable Long id,
                                                                      @RequestBody PreferenciasUpdateDTO preferenciasUpdate) {
        return ResponseEntity.ok(preferenciasService.updatePreferencias(id, preferenciasUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePreferencias(@PathVariable Long id){
        preferenciasService.deletePreferencias(id);
        return ResponseEntity.noContent().build();
    }
}
