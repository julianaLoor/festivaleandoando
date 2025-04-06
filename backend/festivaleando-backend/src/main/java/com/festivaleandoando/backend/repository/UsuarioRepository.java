package com.festivaleandoando.backend.repository;

import com.festivaleandoando.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {  // Verificar si el email ya está registrado
    boolean existsByEmail(String email);
}
