package com.dawes.repositorios;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dawes.modelo.ProveedorBO;

@Repository
public interface ProveedorRepositorio extends CrudRepository<ProveedorBO, Integer> {
	
	//EN EL REPOSITIORIO INTRODUCIMOS TODOS LOS METODOS QUE CONSTULARAN LA BASE DE DATOS DIRECTAMENTE

	//añadimos metodos no incluidos en el CRUD
	ProveedorBO findByNif(String nif);
	
	//utilizaremos una consultad SQL directamente on spring data
	
	//cada vez que modifiquemos un valor ya existente en la BD necesitamos:
	//a Spring mediante Transactional
	/*
	 * Al realizar una insercion en la base de datos en JPA comprobabamos si podiamos
	 * realizar la insercion o no, si podia hacerlo realizaba un commit. Por tanto
	 * dejabamos que toda la gestion la realizase la BD,
	 * 
	 * Mediante Transactional indicamos a Spring que realice toda esta gestion de
	 * validacion (SOLO LO REALIZAREMOS EN CASO DE NO LECTURA)
	 * */
	@Transactional
	//y Modify(indica si podra mnodificar datos que ya estan en la BD)
	@Modifying
	//podemos utilizar los parametros de forma indexeada
	@Query("UPDATE ProveedorBO u SET u.nombre = ?2, u.apellidos = ?3 WHERE u.idproveedor = ?1")
	void actualizarProveedorIndexeado(Integer id,String nuevoNombre, String nuevosApellidos);
	
	//o podemos utilizar los parametros a modo de variables SQL
	@Query("UPDATE ProveedorBO u SET u.nombre = :newNombre, u.apellidos = :newApellidos WHERE u.idproveedor = :id")
	void actualizarProveedorParametrizado(@Param("id") Integer id,@Param("newNombre") String nuevoNombre,@Param("newApellidos") String nuevoApellidos);
}
