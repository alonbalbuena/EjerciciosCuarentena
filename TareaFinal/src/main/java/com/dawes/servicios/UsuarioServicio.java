package com.dawes.servicios;

import java.util.List;
import java.util.Optional;
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
	PasswordEncoder codificador;
	
	@Autowired
	UsuarioRepositorio repositorio;
	
	//METODO IMPLEMENTADO DE UserDetailsService
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repositorio.findByUsuario(username)
				.get(1);//cojemos el primer usuario que encuentre ya que solo puede devolver uno
	}
	
	//implementado de passwordDecoder
	//Crearemos un nuevo usuario con la contraseña codificada y le asignamos un rol
	public Usuario crearNuevoUsuario(Usuario usuarioNuevo) {
		usuarioNuevo.setContraseña(codificador.encode(usuarioNuevo.getPassword()));
		usuarioNuevo.setRoles(Stream
				.of(Rol.USUARIO)
				.collect(Collectors.toSet()));
		return save(usuarioNuevo);
	}


	public List<Usuario> findByUsuario(String usuario) {
		return repositorio.findByUsuario(usuario);
	}

	public <S extends Usuario> S save(S entity) {
		return repositorio.save(entity);
	}

	public <S extends Usuario> Iterable<S> saveAll(Iterable<S> entities) {
		return repositorio.saveAll(entities);
	}

	public Optional<Usuario> findById(Integer id) {
		return repositorio.findById(id);
	}

	public boolean existsById(Integer id) {
		return repositorio.existsById(id);
	}

	public Iterable<Usuario> findAll() {
		return repositorio.findAll();
	}

	public Iterable<Usuario> findAllById(Iterable<Integer> ids) {
		return repositorio.findAllById(ids);
	}

	public long count() {
		return repositorio.count();
	}

	public void deleteById(Integer id) {
		repositorio.deleteById(id);
	}

	public void delete(Usuario entity) {
		repositorio.delete(entity);
	}

	public void deleteAll(Iterable<? extends Usuario> entities) {
		repositorio.deleteAll(entities);
	}

	public void deleteAll() {
		repositorio.deleteAll();
	}
	
	
}
