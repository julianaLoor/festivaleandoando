package com.festivaleandoando.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Tipos")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo;

    @Enumerated(EnumType.STRING) // Esto hace que se guarde el nombre como String
    @Column(length = 50, nullable = false, unique = true)
    private TipoEnum nombreTipo;

    // Getters y Setters

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public TipoEnum getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(TipoEnum nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    // Enum de los tipos de usuario
    public enum TipoEnum {
        CLIENTE,
        PROVEEDOR,
        ADMIN
    }
}
