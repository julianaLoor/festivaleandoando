package com.festivaleandoando.backend.repository;

import com.festivaleandoando.backend.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    // Método para obtener los carritos de un usuario
    List<Carrito> findByUsuarioId_user(Integer idUsuario);
}

