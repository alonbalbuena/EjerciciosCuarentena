package com.dawes.modelo;

public class PostUsuarioDTO {
	private String usuario;
	private String contraseña;

	public PostUsuarioDTO() {
		super();
	}

	public PostUsuarioDTO(String usuario, String contraseña) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
	}
	
	public PostUsuarioDTO(Usuario usuario) {
		this.usuario = usuario.getUsername();
		this.contraseña = usuario.getPassword();
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

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

}
