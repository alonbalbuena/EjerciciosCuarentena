package com.dawes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.modelo.Resultado;
import com.dawes.servicios.Servicio;

//@Controller solo funciona con thymeleaf(creo...)
@RestController
public class ControladorREST {

	@Autowired
	Servicio servicio;
	
	@CrossOrigin(origins = "http://127.0.0.1:5502")
	//permitimos el CORS solo con este metodo
	//(por defecto permite a todo el mundo)
	@GetMapping("/ultimoResultado")
	public Resultado main() {
		return servicio.getUltimoResultado();
	}
}
