package com.festivaleandoando.backend.controller;

import com.festivaleandoando.backend.model.CategoriaFest;
import com.festivaleandoando.backend.repository.CategoriaFestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias_fest")
public class CategoriaFestController {

    @Autowired
    private CategoriaFestRepository categoriaFestRepository;

    // Crear una nueva categoría de festival
    @PostMapping
    public ResponseEntity<CategoriaFest> crearCategoriaFest(@RequestBody CategoriaFest categoriaFest) {
        CategoriaFest nuevaCategoria = categoriaFestRepository.save(categoriaFest);
        return ResponseEntity.ok(nuevaCategoria);
    }

    // Obtener todas las categorías de festivales
    @GetMapping
    public ResponseEntity<List<CategoriaFest>> obtenerTodasLasCategorias() {
        List<CategoriaFest> categorias = categoriaFestRepository.findAll();
        return ResponseEntity.ok(categorias);
    }

    // Obtener una categoría de festival por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaFest> obtenerCategoriaFestPorId(@PathVariable Integer id) {
        return categoriaFestRepository.findById(id)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar categoría de festival
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaFest> actualizarCategoriaFest(@PathVariable Integer id, @RequestBody CategoriaFest categoriaFest) {
        return categoriaFestRepository.findById(id)
                .map(categoriaExistente -> {
                    categoriaExistente.setNombre(categoriaFest.getNombre());
                    CategoriaFest actualizada = categoriaFestRepository.save(categoriaExistente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar categoría de festival
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoriaFest(@PathVariable Integer id) {
        if (categoriaFestRepository.existsById(id)) {
            categoriaFestRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
