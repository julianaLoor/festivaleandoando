package com.festivaleandoando.backend.controller;

import com.festivaleandoando.backend.model.Tipo;
import com.festivaleandoando.backend.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos")
@CrossOrigin(origins = "*") // Habilita llamadas desde el frontend
public class TipoController {

    @Autowired
    private TipoRepository tipoRepository;

    @GetMapping
    public List<Tipo> listarTipos() {
        return tipoRepository.findAll();
    }
}
