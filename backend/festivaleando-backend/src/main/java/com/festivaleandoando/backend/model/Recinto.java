package com.festivaleandoando.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recintos")
public class Recinto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recinto")
    private Integer idRecinto;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "direccion", length = 200)
    private String direccion;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    // Getters y Setters
    public Integer getIdRecinto() {
        return idRecinto;
    }

    public void setIdRecinto(Integer idRecinto) {
        this.idRecinto = idRecinto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
