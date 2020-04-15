package com.dawes.servicios;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelos.ImpuestosVO;
import com.dawes.repositorios.ImpuestoRepositorio;

@Service
public class ImpuestosServicio {

	
	@Autowired
	ImpuestoRepositorio repositorio;

	public Set<ImpuestosVO> findByFecha(LocalDate fecha) {
		return repositorio.findByFecha(fecha);
	}
	
	
}
