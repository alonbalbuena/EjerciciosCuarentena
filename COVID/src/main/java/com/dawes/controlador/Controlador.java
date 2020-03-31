package com.dawes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dawes.servicios.Servicio;

@Controller
public class Controlador {
	
	@Autowired
	Servicio servicio;
	
	@GetMapping("/resultados")
	public String resultados(Model modelo) {
		modelo.addAttribute("json", servicio.getUltimoResultado());
		modelo.addAttribute("casosTotales",servicio.getCasosTotales());
		return "resultados";
	}
}
