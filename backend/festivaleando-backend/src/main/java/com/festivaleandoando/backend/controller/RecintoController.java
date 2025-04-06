package com.festivaleandoando.backend.controller;

import com.festivaleandoando.backend.model.Recinto;
import com.festivaleandoando.backend.repository.RecintoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recintos")
public class RecintoController {

    @Autowired
    private RecintoRepository recintoRepository;

    // Crear un nuevo recinto
    @PostMapping
    public ResponseEntity<Recinto> crearRecinto(@RequestBody Recinto recinto) {
        Recinto nuevoRecinto = recintoRepository.save(recinto);
        return ResponseEntity.ok(nuevoRecinto);
    }

    // Obtener todos los recintos
    @GetMapping
    public ResponseEntity<List<Recinto>> obtenerTodosLosRecintos() {
        List<Recinto> recintos = recintoRepository.findAll();
        return ResponseEntity.ok(recintos);
    }

    // Obtener un recinto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Recinto> obtenerRecintoPorId(@PathVariable Integer id) {
        return recintoRepository.findById(id)
                .map(recinto -> ResponseEntity.ok(recinto))
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar recinto
    @PutMapping("/{id}")
    public ResponseEntity<Recinto> actualizarRecinto(@PathVariable Integer id, @RequestBody Recinto recinto) {
        return recintoRepository.findById(id)
                .map(recintoExistente -> {
                    recintoExistente.setNombre(recinto.getNombre());
                    recintoExistente.setDireccion(recinto.getDireccion());
                    recintoExistente.setCapacidad(recinto.getCapacidad());
                    Recinto actualizado = recintoRepository.save(recintoExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar recinto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecinto(@PathVariable Integer id) {
        if (recintoRepository.existsById(id)) {
            recintoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
