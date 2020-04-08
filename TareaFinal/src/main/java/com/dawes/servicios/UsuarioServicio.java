package com.dawes.servicios;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dawes.modelo.Usuario;
import com.dawes.repositorios.UsuarioRepositorio;
import com.dawes.seguridad.Rol;
@Service
public class UsuarioServicio implements UserDetailsService{

	@Autowired
	private PasswordEncoder codificador;
	
	@Autowired
	private UsuarioRepositorio repositorio;
	
	//METODO IMPLEMENTADO DE UserDetailsService
	//cada vez que neceistamos usar las credenciales de un usuario pasamos su nombre
	//y Spring nos devolvera todas las credenciales de dicho usuario
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repositorio.findByUsuario(username);//cojemos el primer usuario que encuentre ya que solo puede devolver uno
	}
	
	//implementado de passwordDecoder
	//Crearemos un nuevo usuario con la contraseña codificada y le asignamos un rol
	public Usuario crearNuevoUsuario(Usuario usuarioNuevo) {
		usuarioNuevo.setContraseña(codificador.encode(usuarioNuevo.getPassword()));
		usuarioNuevo.setRoles(
				Stream.of(Rol.USUARIO)
				.collect(Collectors.toSet()));
		return save(usuarioNuevo);
	}


	public Usuario findByUsuario(String usuario) {
		return repositorio.findByUsuario(usuario);
	}

	public <S extends Usuario> S save(S entity) {
		return repositorio.save(entity);
	}

	public Iterable<Usuario> findAll() {
		return repositorio.findAll();
	}
	
}
