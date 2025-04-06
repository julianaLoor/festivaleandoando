package com.festivaleandoando.backend.repository;

import com.festivaleandoando.backend.model.Recinto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecintoRepository extends JpaRepository<Recinto, Integer> {
    // Aquí puedes agregar métodos adicionales si necesitas
}
