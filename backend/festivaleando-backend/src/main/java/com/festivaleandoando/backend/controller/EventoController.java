package com.festivaleandoando.backend.controller;

import com.festivaleandoando.backend.model.Evento;
import com.festivaleandoando.backend.repository.EventoRepository;
import com.festivaleandoando.backend.repository.RecintoRepository;
import com.festivaleandoando.backend.repository.CategoriaFestRepository;
import com.festivaleandoando.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private RecintoRepository recintoRepository;

    @Autowired
    private CategoriaFestRepository categoriaFestRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear un evento
    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento) {
        // Validar que los datos necesarios existan
        if (evento.getRecinto() == null || evento.getCategoria() == null || evento.getUsuario() == null) {
            return ResponseEntity.status(400).build(); // Error si faltan campos
        }

        // Guardar el evento en la base de datos
        Evento nuevoEvento = eventoRepository.save(evento);
        return ResponseEntity.ok(nuevoEvento);
    }

    // Obtener todos los eventos
    @GetMapping
    public ResponseEntity<List<Evento>> obtenerTodosLosEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        return ResponseEntity.ok(eventos);
    }

    // Obtener un evento por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerEventoPorId(@PathVariable Integer id) {
        return eventoRepository.findById(id)
                .map(evento -> ResponseEntity.ok(evento))
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar un evento
    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizarEvento(@PathVariable Integer id, @RequestBody Evento evento) {
        return eventoRepository.findById(id)
                .map(eventoExistente -> {
                    eventoExistente.setNombre(evento.getNombre());
                    eventoExistente.setDescripcion(evento.getDescripcion());
                    eventoExistente.setFechaHora(evento.getFechaHora());
                    eventoExistente.setEntradasDisponibles(evento.getEntradasDisponibles());
                    eventoExistente.setRecinto(evento.getRecinto());
                    eventoExistente.setCategoria(evento.getCategoria());
                    eventoExistente.setUsuario(evento.getUsuario());

                    Evento actualizado = eventoRepository.save(eventoExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Integer id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
