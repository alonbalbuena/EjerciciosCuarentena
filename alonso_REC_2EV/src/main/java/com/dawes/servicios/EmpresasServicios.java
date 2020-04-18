package com.dawes.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelos.EmpresaVO;
import com.dawes.repositorios.EmpresaRepositorio;

@Service
public class EmpresasServicios {

	@Autowired
	private EmpresaRepositorio repositorio;

	public Iterable<EmpresaVO> findAll() {
		return repositorio.findAll();
	}

	public Optional<EmpresaVO> findById(Integer id) {
		return repositorio.findById(id);
	}
	
	
}
