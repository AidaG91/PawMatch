package com.example.demo.repository;

import com.example.demo.model.Preferencias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenciasRepository extends JpaRepository<Preferencias, Long> {
}
