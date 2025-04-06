package com.festivaleandoando.backend;

import com.festivaleandoando.backend.model.Tipo;
import com.festivaleandoando.backend.model.Usuario;
import com.festivaleandoando.backend.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.festivaleandoando.backend.repository.TipoRepository;
import java.time.LocalDate;

@SpringBootApplication
public class FestivaleandoandoApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TipoRepository tipoRepository;

	public static void main(String[] args) {
		SpringApplication.run(FestivaleandoandoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (tipoRepository.count() == 0) {
			Tipo tipoCliente = new Tipo();
			tipoCliente.setNombreTipo(Tipo.TipoEnum.CLIENTE);
			tipoRepository.save(tipoCliente);

			Usuario usuario = new Usuario();
			usuario.setNombre("Juliana");
			usuario.setApellidos("Loor Montoya");
			usuario.setEmail("juliana@example.com");
			usuario.setContrasena("clave1234");
			usuario.setFechaNacimiento(LocalDate.of(2000, 5, 20));
			usuario.setTipo(tipoCliente);

			usuarioRepository.save(usuario);
		}
	}
}
