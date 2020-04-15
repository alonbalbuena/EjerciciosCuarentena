package com.dawes.repositorios;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelos.ImpuestosVO;
@Repository
public interface ImpuestoRepositorio extends CrudRepository<ImpuestosVO, Integer> {


	/*Creamos el metodo para poder buscar por fechas*/
	Set<ImpuestosVO> findByFecha(LocalDate fecha);
}
