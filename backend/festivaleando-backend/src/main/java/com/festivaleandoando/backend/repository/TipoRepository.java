package com.festivaleandoando.backend.repository;

import com.festivaleandoando.backend.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    Tipo findByNombreTipo(String nombreTipo);  // Este método ya está bien, solo asegúrate que reciba String
}
