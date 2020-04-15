package com.dawes.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.dawes.modelos.ImpuestosVO;

@Controller
public class Controlador {

	 @Autowired
	 private RestTemplate peticion;
	
	@RequestMapping("/inicio")
	public String indice() {
		return "formFechas";
	}
	
	@RequestMapping("/mostrar")
	public String mostrarImpuestos(@RequestParam("fecha1"),Model modelo) {
		ImpuestosVO[] impuestos = peticion.getForObject("localhost:8080/impuestos",ImpuestosVO[].class);
		modelo.addAttribute("impuestos", impuestos);
		return "listaImpuestos";
	}
}
