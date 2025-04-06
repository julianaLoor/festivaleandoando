package com.festivaleandoando.backend.repository;

import com.festivaleandoando.backend.model.ResumenCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumenCompraRepository extends JpaRepository<ResumenCompra, Integer> {
    // Método para obtener todos los resúmenes de compra asociados a una compra
    List<ResumenCompra> findByCompraIdCompra(Integer idCompra);
}

