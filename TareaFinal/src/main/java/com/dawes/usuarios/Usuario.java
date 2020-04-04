package com.dawes.usuarios;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails{

	private static final long serialVersionUID = -1057226606532692341L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String usuario;
	private String contraseña;
	//usaremos una enumeracion en vez de una tabla
	@ElementCollection//igual que si usasemos manyToOne
	@Enumerated(EnumType.STRING)//considera los objetos dentro del enum Strings
	private Set<Rol> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//// @formatter:off
		//devolvemos los permisos que tiene el usuario en este caso los roles
		return roles
				.stream()
				.map(rol -> new SimpleGrantedAuthority("ROL_"+ rol.name()))//le damos autoridad a todos rol
				//Simplegrantedauthority guarda una representacion de la autenticacion
				//(UsernamePasswordAutheticationtoken)  dada a este objeto
				.collect(Collectors.toList());//el metodo devuelve un conjunto de autorizaciones
		// @formatter:on
	}
	@Override
	public String getPassword() {
		return contraseña;
	}
	@Override
	public String getUsername() {
		return usuario;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
