package com.festivaleandoando.backend.repository;

import com.festivaleandoando.backend.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}

