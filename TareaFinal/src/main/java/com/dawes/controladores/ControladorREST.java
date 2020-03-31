package com.dawes.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.modelo.ProveedorBO;
import com.dawes.servicios.ProveedorServicios;

@RestController
public class ControladorREST {

	@Autowired
	ProveedorServicios servicios;
	
	//la respuesta del metodo es un conjunto de objetos (encapsulado en el responsebody)
	@GetMapping("/proveedores")
	public Iterable<ProveedorBO> mostrarProveedores() {
		return servicios.findAll();
	}
	
	//sacamos el nif de la url y lo pasamos como parametro al metodo
	//la respuesta del metodo es un solo objeto (encapsulado en el responsebody)
	@GetMapping("/proveedor/{nif}")
	public ProveedorBO mostrarProveedor(@PathVariable(name = "nif") String nif){
		return servicios.findByNif(nif);
	}
	
	//Creamos un proveedor con el cuerpo de la peticion HTTP que lo pasamos como parametro
	//no devuelve nada porque insertamos un valor
	@PostMapping("/proveedor")
	public void insertarProveedor(@RequestBody ProveedorBO proveedor) {
		servicios.save(proveedor);
	}
	
	
	//segun el estandar HTTP de 2010 el verbo para actualizar un solo valor sera PATH
	//pero como Spring no lo soporta todavia usaremos put que se usa normalmente
	//para actualizar todos los valores
	//pasaremos el id por la URL y los valores que queremos actualizar por el body de la peticion
	@PutMapping("/proovedor/{idProveedor}")
	public void actualizarProveedor(@PathVariable(name = "idProveedor") Integer idProveedor ,@RequestBody ProveedorBO proveedorActualizado) {
		//utilizaremos indistintivamente el metodo indexeado o el parametrizado(para ver mas mirar ProveedorRepositorio)
		servicios.actualizarProveedorIndexeado(idProveedor, proveedorActualizado.getNombre(), proveedorActualizado.getApellidos());
	}
}
