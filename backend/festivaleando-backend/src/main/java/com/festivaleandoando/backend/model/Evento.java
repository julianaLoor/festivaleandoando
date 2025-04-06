package com.festivaleandoando.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer idEvento;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "entradas_disponibles", nullable = false)
    private Integer entradasDisponibles;

    @ManyToOne
    @JoinColumn(name = "id_recinto", nullable = false)
    private Recinto recinto;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaFest categoria;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Usuario usuario; // organizador

    // Getters y Setters
    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getEntradasDisponibles() {
        return entradasDisponibles;
    }

    public void setEntradasDisponibles(Integer entradasDisponibles) {
        this.entradasDisponibles = entradasDisponibles;
    }

    public Recinto getRecinto() {
        return recinto;
    }

    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }

    public CategoriaFest getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaFest categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
