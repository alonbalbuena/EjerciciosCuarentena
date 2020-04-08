package com.dawes.ws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.modelo.GetUsuarioDTO;
import com.dawes.modelo.Usuario;
import com.dawes.servicios.UsuarioServicio;

@RestController
public class UsuarioRest {
	
	@Autowired
	UsuarioServicio servicio;
	
	@GetMapping("/usuario")
	public List<GetUsuarioDTO> mostrarUsuarios() {
		List<GetUsuarioDTO> lista = new ArrayList<GetUsuarioDTO>();
		for (Usuario usuario : servicio.findAll()) {
			lista.add(new GetUsuarioDTO(usuario));
		}
		return lista;
	}

	@PostMapping("/usuario")
	public void nuevoUsuario(@RequestBody Usuario nuevoUsuario) {
		servicio.save(servicio.crearNuevoUsuario(nuevoUsuario));
	}

	@GetMapping("/yo") // @Auth... nos devuelve las credenciales del usuario actualmente autenticado
	public GetUsuarioDTO yo(@AuthenticationPrincipal Usuario usuario) {
		return new GetUsuarioDTO(usuario);
	}
}
