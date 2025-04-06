package com.festivaleandoando.backend.controller;

import com.festivaleandoando.backend.model.Usuario;
import com.festivaleandoando.backend.model.Tipo;
import com.festivaleandoando.backend.repository.UsuarioRepository;
import com.festivaleandoando.backend.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoRepository tipoRepository; // Necesitamos el repositorio de Tipo

    // REGISTRO de usuario nuevo (por defecto tipo CLIENTE)
    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.status(400).body(null); // O enviar un mensaje personalizado
        }

        // Buscar el tipo "cliente" en la base de datos utilizando el enum
        Tipo tipoCliente = tipoRepository.findByNombreTipo(Tipo.TipoEnum.CLIENTE.name());

        // Si el tipo "cliente" no existe, devolver error
        if (tipoCliente == null) {
            return ResponseEntity.status(400).build(); // Puedes personalizar el mensaje de error si lo deseas
        }

        usuario.setTipo(tipoCliente); // Asignar el tipo cliente
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Obtener todos los usuarios (Solo ADMIN)
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
        return usuarioRepository.findById(id)
                .map(usuario -> ResponseEntity.ok(usuario))
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNombre(usuario.getNombre());
                    usuarioExistente.setApellidos(usuario.getApellidos());
                    usuarioExistente.setEmail(usuario.getEmail());
                    usuarioExistente.setFechaNacimiento(usuario.getFechaNacimiento());
                    Usuario actualizado = usuarioRepository.save(usuarioExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
