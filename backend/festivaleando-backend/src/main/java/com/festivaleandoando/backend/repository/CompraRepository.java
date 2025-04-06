package com.festivaleandoando.backend.repository;

import com.festivaleandoando.backend.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
    // Método para obtener todas las compras de un usuario
    List<Compra> findByUsuarioId_user(Integer idUser);
}

