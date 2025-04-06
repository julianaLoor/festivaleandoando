package com.festivaleandoando.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_entradas")
public class CarritoEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carr_entrada")
    private Integer idCarrEntrada;

    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "id_entrada", nullable = false)
    private Entrada entrada;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    // Getters y Setters
    public Integer getIdCarrEntrada() {
        return idCarrEntrada;
    }

    public void setIdCarrEntrada(Integer idCarrEntrada) {
        this.idCarrEntrada = idCarrEntrada;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
