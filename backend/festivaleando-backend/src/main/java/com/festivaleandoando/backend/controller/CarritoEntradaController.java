package com.festivaleandoando.backend.controller;

import com.festivaleandoando.backend.model.CarritoEntrada;
import com.festivaleandoando.backend.repository.CarritoEntradaRepository;
import com.festivaleandoando.backend.repository.CarritoRepository;
import com.festivaleandoando.backend.repository.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito-entradas")
@CrossOrigin(origins = "*")
public class CarritoEntradaController {

    @Autowired
    private CarritoEntradaRepository carritoEntradaRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    // Agregar entrada al carrito
    @PostMapping
    public ResponseEntity<CarritoEntrada> agregarEntradaCarrito(@RequestBody CarritoEntrada carritoEntrada) {
        // Verificar que el carrito exista
        if (!carritoRepository.existsById(carritoEntrada.getCarrito().getIdCarrito())) {
            return ResponseEntity.status(404).build(); // Si el carrito no existe, devolver error
        }

        // Verificar que la entrada exista
        if (!entradaRepository.existsById(carritoEntrada.getEntrada().getIdEntrada())) {
            return ResponseEntity.status(404).build(); // Si la entrada no existe, devolver error
        }

        // Guardar la entrada en el carrito
        CarritoEntrada nuevaCarritoEntrada = carritoEntradaRepository.save(carritoEntrada);
        return ResponseEntity.ok(nuevaCarritoEntrada);
    }

    // Obtener todas las entradas de un carrito
    @GetMapping("/carrito/{idCarrito}")
    public ResponseEntity<List<CarritoEntrada>> obtenerEntradasPorCarrito(@PathVariable Integer idCarrito) {
        if (!carritoRepository.existsById(idCarrito)) {
            return ResponseEntity.status(404).build(); // Si el carrito no existe, devolver error
        }

        List<CarritoEntrada> entradas = carritoEntradaRepository.findByCarritoIdCarrito(idCarrito);
        return ResponseEntity.ok(entradas);
    }

    // Actualizar la cantidad de una entrada en el carrito
    @PutMapping("/{id}")
    public ResponseEntity<CarritoEntrada> actualizarCantidadEntrada(@PathVariable Integer id, @RequestBody CarritoEntrada carritoEntrada) {
        return carritoEntradaRepository.findById(id)
                .map(carritoEntradaExistente -> {
                    carritoEntradaExistente.setCantidad(carritoEntrada.getCantidad());
                    CarritoEntrada actualizado = carritoEntradaRepository.save(carritoEntradaExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar entrada del carrito
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntradaCarrito(@PathVariable Integer id) {
        if (carritoEntradaRepository.existsById(id)) {
            carritoEntradaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
