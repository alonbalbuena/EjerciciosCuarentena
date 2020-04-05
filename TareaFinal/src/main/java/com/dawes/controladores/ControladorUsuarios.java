package com.dawes.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.modelo.GetUsuarioDTO;
import com.dawes.modelo.Usuario;
import com.dawes.servicios.UsuarioServicio;

@RestController
public class ControladorUsuarios {

	@Autowired
	UsuarioServicio servicio;
	
	@GetMapping("/usuario")
	public List<GetUsuarioDTO> mostrarUsuarios(){
		List<GetUsuarioDTO> lista = new ArrayList<GetUsuarioDTO>();
		for (Usuario usuario : servicio.findAll()) {
			lista.add(new GetUsuarioDTO(usuario));
		}
		return lista;
	}
	
	@PostMapping("/usuario")
	public void nuevoUsuario(@RequestBody Usuario nuevoUsuario) {
		servicio.save(nuevoUsuario);
	}
}
