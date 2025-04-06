package com.festivaleandoando.backend.controller;

import com.festivaleandoando.backend.model.ResumenCompra;
import com.festivaleandoando.backend.repository.ResumenCompraRepository;
import com.festivaleandoando.backend.repository.CompraRepository;
import com.festivaleandoando.backend.repository.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumen-compra")
@CrossOrigin(origins = "*") // Habilita las solicitudes desde el frontend
public class ResumenCompraController {

    @Autowired
    private ResumenCompraRepository resumenCompraRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    // Crear un nuevo resumen de compra
    @PostMapping
    public ResponseEntity<ResumenCompra> crearResumenCompra(@RequestBody ResumenCompra resumenCompra) {
        // Verificar que la compra exista
        if (!compraRepository.existsById(resumenCompra.getCompra().getIdCompra())) {
            return ResponseEntity.status(404).build(); // Si la compra no existe, devolver error
        }

        // Verificar que la entrada exista
        if (!entradaRepository.existsById(resumenCompra.getEntrada().getIdEntrada())) {
            return ResponseEntity.status(404).build(); // Si la entrada no existe, devolver error
        }

        ResumenCompra nuevoResumen = resumenCompraRepository.save(resumenCompra);
        return ResponseEntity.ok(nuevoResumen);
    }

    // Obtener todos los detalles de resumen de compra por compra
    @GetMapping("/compra/{idCompra}")
    public ResponseEntity<List<ResumenCompra>> obtenerResumenPorCompra(@PathVariable Integer idCompra) {
        List<ResumenCompra> resumenes = resumenCompraRepository.findByCompraIdCompra(idCompra);
        return ResponseEntity.ok(resumenes);
    }

    // Eliminar un resumen de compra
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResumenCompra(@PathVariable Integer id) {
        if (resumenCompraRepository.existsById(id)) {
            resumenCompraRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
