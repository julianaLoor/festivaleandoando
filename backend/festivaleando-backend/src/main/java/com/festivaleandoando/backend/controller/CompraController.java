package com.festivaleandoando.backend.controller;

import com.festivaleandoando.backend.model.Compra;
import com.festivaleandoando.backend.repository.CompraRepository;
import com.festivaleandoando.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*") // Habilita las solicitudes desde el frontend
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear una nueva compra
    @PostMapping
    public ResponseEntity<Compra> crearCompra(@RequestBody Compra compra) {
        // Verificar que el usuario exista
        if (!usuarioRepository.existsById(compra.getUsuario().getId_user())) {
            return ResponseEntity.status(404).build(); // Si el usuario no existe, devolver error
        }

        // Calcular el total de la compra si es necesario, o asignarlo desde el request
        if (compra.getTotal() == null) {
            return ResponseEntity.badRequest().build(); // Si el total no está presente, devolver error
        }

        Compra nuevaCompra = compraRepository.save(compra);
        return ResponseEntity.ok(nuevaCompra);
    }

    // Obtener todas las compras de un usuario
    @GetMapping("/usuario/{idUser}")
    public ResponseEntity<List<Compra>> obtenerComprasPorUsuario(@PathVariable Integer idUser) {
        if (!usuarioRepository.existsById(idUser)) {
            return ResponseEntity.status(404).build(); // Si el usuario no existe, devolver error
        }

        List<Compra> compras = compraRepository.findByUsuarioId_user(idUser);
        return ResponseEntity.ok(compras);
    }

    // Actualizar estado de compra
    @PutMapping("/{id}")
    public ResponseEntity<Compra> actualizarEstadoCompra(@PathVariable Integer id, @RequestBody Compra compra) {
        return compraRepository.findById(id)
                .map(compraExistente -> {
                    compraExistente.setEstado(compra.getEstado());
                    Compra compraActualizada = compraRepository.save(compraExistente);
                    return ResponseEntity.ok(compraActualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar compra
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Integer id) {
        if (compraRepository.existsById(id)) {
            compraRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
