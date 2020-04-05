package com.dawes.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.Usuario;
@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {

	//devuelve una lista porque puede haber varios con el mismo nombre
	List<Usuario> findByUsuario(String usuario);
}
