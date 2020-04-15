package com.dawes.controladores;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.dawes.modelo.PostUsuarioDTO;
import com.dawes.modelo.Usuario;
import com.dawes.servicios.UsuarioServicio;

@Controller
public class Controlador {
	
	@Autowired//inyeccion de dependencia esta en la clase de configuracion de seguridad
	private RestTemplate template;
	
	@Autowired
	private UsuarioServicio servicio;
	
	@RequestMapping("/logeo")
	public String login() {
		return "index";
	}
	
	@RequestMapping("/registro")
	public String formularioRegistro(Model modelo) {
		modelo.addAttribute("usuarioNuevo",new Usuario());
		return "registro";
	}
	
	@RequestMapping(value = "/crearUsuario", method = RequestMethod.POST)
	public String crearUsuario(@ModelAttribute(name = "usuarioNuevo") Usuario usuarioNuevo,Model modelo) {
		
		PostUsuarioDTO usuarioPostDto = new PostUsuarioDTO(usuarioNuevo);
		
		//verificamos que el usuario no existe ya antes de introducirlo
		if servicio.findByUsuario(usuarioNuevo.getUsername())
		
		//necesitamos enviar la peticion con los campos del JSON **solamente necesario**
		//en este caso la peticion seria 
		//{ "usuario":"123","contraseña":"123"}
		template.postForObject("http://localhost:8080/usuario", usuarioPostDto, String.class);
		return "index";
	}
}
