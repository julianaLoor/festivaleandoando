package com.festivaleandoando.backend.controller;

import com.festivaleandoando.backend.model.Entrada;
import com.festivaleandoando.backend.repository.EntradaRepository;
import com.festivaleandoando.backend.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entradas")
@CrossOrigin(origins = "*")
public class EntradaController {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    // Crear una nueva entrada
    @PostMapping
    public ResponseEntity<Entrada> crearEntrada(@RequestBody Entrada entrada) {
        if (entrada.getEvento() == null) {
            return ResponseEntity.status(400).build(); // Error si el evento no es válido
        }

        // Verificar si el evento existe
        if (!eventoRepository.existsById(entrada.getEvento().getIdEvento())) {
            return ResponseEntity.status(404).build(); // Si el evento no existe, devolver error
        }

        // Guardar la entrada en la base de datos
        Entrada nuevaEntrada = entradaRepository.save(entrada);
        return ResponseEntity.ok(nuevaEntrada);
    }

    // Obtener todas las entradas
    @GetMapping
    public ResponseEntity<List<Entrada>> obtenerTodasLasEntradas() {
        List<Entrada> entradas = entradaRepository.findAll();
        return ResponseEntity.ok(entradas);
    }

    // Obtener las entradas de un evento por su ID
    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<List<Entrada>> obtenerEntradasPorEvento(@PathVariable Integer idEvento) {
        if (!eventoRepository.existsById(idEvento)) {
            return ResponseEntity.status(404).build(); // Si el evento no existe, devolver error
        }

        List<Entrada> entradas = entradaRepository.findByEventoIdEvento(idEvento);
        return ResponseEntity.ok(entradas);
    }

    // Actualizar estado de una entrada
    @PutMapping("/{id}")
    public ResponseEntity<Entrada> actualizarEstadoEntrada(@PathVariable Integer id, @RequestBody Entrada entrada) {
        return entradaRepository.findById(id)
                .map(entradaExistente -> {
                    entradaExistente.setEstado(entrada.getEstado());
                    Entrada entradaActualizada = entradaRepository.save(entradaExistente);
                    return ResponseEntity.ok(entradaActualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar entrada
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrada(@PathVariable Integer id) {
        if (entradaRepository.existsById(id)) {
            entradaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
