package com.dawes.modelo;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dawes.seguridad.Rol;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="usuarios")
public class Usuario implements UserDetails{

	private static final long serialVersionUID = -1057226606532692341L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String usuario;
	private String contraseña;
	//usaremos una enumeracion en vez de una tabla
	@ElementCollection(fetch = FetchType.EAGER)//igual que si usasemos manyToOne
	@Enumerated(EnumType.STRING)//considera los objetos dentro del enum Strings
	private Set<Rol> roles;
	
	
	
	public Usuario(String usuario, String contraseña) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
	}

	public Usuario() {
		super();
	}

	public Usuario(String usuario, String contraseña, Set<Rol> roles) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.roles = roles;
	}

	public Usuario(Integer id, String usuario, String contraseña, Set<Rol> roles) {
		this.id = id;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.roles = roles;
	}

	@Override//devuelve todas las autorizaciones que tiene el usuario en este caso son Simples
	//por lo tanto recorre todos los roles de la BBDD que tiene y los convierte a autorizacionesimples
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//// @formatter:off
		//devolvemos los permisos que tiene el usuario en este caso los roles
		return roles
				.stream()
				.map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.name()))//le damos autoridad a todos rol
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

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public String getUsuario() {
		return getUsername();
	}

	public String getContraseña() {
		return getPassword();
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

	public Set<Rol> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", contraseña=" + contraseña + ", roles=" + roles + "]";
	}
	
	
}
