package com.dawes.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.ProveedorBO;

@Repository
public interface ProveedorRepositorio extends CrudRepository<ProveedorBO, Integer> {
	
	//EN EL REPOSITIORIO INTRODUCIMOS TODOS LOS METODOS QUE CONSTULARAN LA BASE DE DATOS DIRECTAMENTE

	//añadimos metodos no incluidos en el CRUD
	ProveedorBO findByNif(String nif);
	
	//utilizaremos una consultad SQL directamente on spring data
	
	//podemos utilizar los parametros de forma indexeada
	@Query("UPDATE ProveedorBO SET nombre = ?2, apellidos = ?3 WHERE idproveedor = ?1")
	void actualizarProveedorIndexeado(Integer id,String nuevoNombre, String nuevosApellidos);
	
	//o podemos utilizar los parametros a modo de variables SQL
	@Query("UPDATE ProveedorBO SET nombre = :newNombre, apellidos = :newApellidos WHERE idproveedor = :id")
	void actualizarProveedorParametrizado(@Param("id") Integer id,@Param("newNombre") String nuevoNombre,@Param("newApellidos") String nuevoApellidos);
}
