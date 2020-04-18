package com.dawes.servicios;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelos.EmpresaVO;
import com.dawes.modelos.ImpuestosVO;
import com.dawes.repositorios.ImpuestoRepositorio;

@Service
public class ImpuestosServicio {

	
	@Autowired
	ImpuestoRepositorio repositorio;

	public Set<ImpuestosVO> buscarEntreFechas(LocalDate fechaInicial,LocalDate fechaFinal) {
		return repositorio.buscarEntreFechas(fechaInicial, fechaFinal);
	}

	public Set<ImpuestosVO> findByEmpresa(Optional<EmpresaVO> optional) {
		return repositorio.findByEmpresa(optional);
	}

	public Optional<ImpuestosVO> findById(Integer id) {
		return repositorio.findById(id);
	}

	public void actualizarImpuesto(Integer idImpuesto, Integer baseImponible, LocalDate fecha) {
		repositorio.actualizarImpuesto(idImpuesto, baseImponible, fecha);
	}
	
	
	
}
