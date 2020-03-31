package com.dawes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelo.ProveedorBO;
import com.dawes.repositorios.ProveedorRepositorio;

@Service
public class ProveedorServicios {

	@Autowired
	ProveedorRepositorio repositorio;

	public Iterable<ProveedorBO> findAll() {
		return repositorio.findAll();
	}
	
	public <S extends ProveedorBO> S save(S entity) {
		return repositorio.save(entity);
	}

	public ProveedorBO findByNif(String nif) {
		return repositorio.findByNif(nif);
	}

	public void actualizarProveedorIndexeado(Integer id, String nuevoNombre, String nuevosApellidos) {
		repositorio.actualizarProveedorIndexeado(id, nuevoNombre, nuevosApellidos);
	}

	public void actualizarProveedorParametrizado(Integer id, String nuevoNombre, String nuevoApellidos) {
		repositorio.actualizarProveedorParametrizado(id, nuevoNombre, nuevoApellidos);
	}

	public void deleteById(Integer id) {
		repositorio.deleteById(id);
	}	
}
