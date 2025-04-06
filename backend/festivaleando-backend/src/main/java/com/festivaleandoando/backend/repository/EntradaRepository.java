package com.festivaleandoando.backend.repository;

import com.festivaleandoando.backend.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {
    // Método para obtener las entradas de un evento
    List<Entrada> findByEventoIdEvento(Integer idEvento);
}

