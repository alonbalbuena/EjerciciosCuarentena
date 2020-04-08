package com.dawes.repositorios;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.Usuario;
@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {

	Usuario findByUsuario(String usuario);
}
