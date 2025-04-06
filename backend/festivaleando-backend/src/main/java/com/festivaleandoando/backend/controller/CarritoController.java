package com.festivaleandoando.backend.controller;

import com.festivaleandoando.backend.model.Carrito;
import com.festivaleandoando.backend.repository.CarritoRepository;
import com.festivaleandoando.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear un carrito nuevo
    @PostMapping
    public ResponseEntity<Carrito> crearCarrito(@RequestBody Carrito carrito) {
        // Verificar si el usuario existe
        if (!usuarioRepository.existsById(carrito.getUsuario().getId_user())) {
            return ResponseEntity.status(404).build(); // Si el usuario no existe, devolver error
        }

        // Establecer estado como ACTIVO por defecto
        carrito.setEstado(Carrito.EstadoCarrito.ACTIVO);

        // Guardar el carrito en la base de datos
        Carrito nuevoCarrito = carritoRepository.save(carrito);
        return ResponseEntity.ok(nuevoCarrito);
    }

    // Obtener todos los carritos
    @GetMapping
    public ResponseEntity<List<Carrito>> obtenerTodosLosCarritos() {
        List<Carrito> carritos = carritoRepository.findAll();
        return ResponseEntity.ok(carritos);
    }

    // Obtener los carritos de un usuario por ID
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Carrito>> obtenerCarritosPorUsuario(@PathVariable Integer idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            return ResponseEntity.status(404).build(); // Si el usuario no existe, devolver error
        }

        List<Carrito> carritos = carritoRepository.findByUsuarioId_user(idUsuario);
        return ResponseEntity.ok(carritos);
    }

    // Actualizar el estado de un carrito (de ACTIVO a FINALIZADO)
    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizarEstadoCarrito(@PathVariable Integer id, @RequestBody Carrito carrito) {
        return carritoRepository.findById(id)
                .map(carritoExistente -> {
                    carritoExistente.setEstado(carrito.getEstado());
                    Carrito carritoActualizado = carritoRepository.save(carritoExistente);
                    return ResponseEntity.ok(carritoActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un carrito
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Integer id) {
        if (carritoRepository.existsById(id)) {
            carritoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
