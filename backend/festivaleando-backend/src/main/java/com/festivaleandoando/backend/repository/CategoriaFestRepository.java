package com.festivaleandoando.backend.repository;

import com.festivaleandoando.backend.model.CategoriaFest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaFestRepository extends JpaRepository<CategoriaFest, Integer> {
    // Puedes agregar otros métodos si es necesario
}

