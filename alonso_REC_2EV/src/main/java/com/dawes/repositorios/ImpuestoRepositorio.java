package com.dawes.repositorios;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dawes.modelos.EmpresaVO;
import com.dawes.modelos.ImpuestosVO;
@Repository
public interface ImpuestoRepositorio extends CrudRepository<ImpuestosVO, Integer> {


	/*Creamos el metodo para poder buscar por fechas*/
	@Query("SELECT u FROM ImpuestosVO u WHERE u.fecha BETWEEN :fechaInicial AND :fechaFinal")
	Set<ImpuestosVO> buscarEntreFechas(@Param("fechaInicial") LocalDate fechaInicial,
			@Param("fechaFinal") LocalDate fechaFinal);
	
	Set<ImpuestosVO> findByEmpresa(Optional<EmpresaVO> empresa);
	
	@Modifying//para permitir operaciones DML
	@Transactional
	@Query("UPDATE ImpuestosVO u SET u.baseimponible = ?2, u.fecha = ?3 WHERE u.idimpuesto = ?1")
	void actualizarImpuesto(Integer idImpuesto,Integer baseImponible, LocalDate fecha);
}
