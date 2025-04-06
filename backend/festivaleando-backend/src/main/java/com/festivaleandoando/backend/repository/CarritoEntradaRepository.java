package com.festivaleandoando.backend.repository;

import com.festivaleandoando.backend.model.CarritoEntrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoEntradaRepository extends JpaRepository<CarritoEntrada, Integer> {
    // Método para obtener las entradas de un carrito
    List<CarritoEntrada> findByCarritoIdCarrito(Integer idCarrito);
}

