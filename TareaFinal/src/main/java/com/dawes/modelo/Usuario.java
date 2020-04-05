package com.dawes.modelo;

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
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dawes.seguridad.Rol;

@Entity
@Table(name="usuarios")
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
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
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
