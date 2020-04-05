package com.dawes.modelo;

import java.util.Set;
import java.util.stream.Collectors;

import com.dawes.seguridad.Rol;

public class GetUsuarioDTO {

	private String nombre;
	private Set<String> roles;
	
	public GetUsuarioDTO(Usuario usuario) {
		nombre = usuario.getUsername();
		roles = usuario.getRoles()
				.stream()
				.map(Rol::name)
				.collect(Collectors.toSet());
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	
	
	
}
