package com.dawes.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.dawes.modelo.ProveedorBO;
import com.dawes.servicios.ProveedorServicios;

@Controller
public class Controlador {

	@Autowired
	ProveedorServicios servicio;
	
	//Coje la ruta del inicio tras el login
	//pero devuelve otro html
	@RequestMapping("/inicio")
	public String inicio(Model modelo){
		//generamos una peticion HTTP que nos devuelva los proveedores
		
		//1ª MANERA
		RestTemplate plantillaPeticiones = new RestTemplate();
		//getForObject no permite enviar listas de objetos(podriamos crear un wrapper) pero si arrays
		ProveedorBO[] proveedores = plantillaPeticiones.getForObject("http://localhost:8080/proveedores", ProveedorBO[].class);
		//le pasamos todos los proveedores
		modelo.addAttribute("proveedores",proveedores);
		
		return "proveedores";
	}
	
	@RequestMapping("/eliminarProveedor")
	public String eliminar(Model modelo,@RequestParam Integer id){
		//generamos una peticion HTTP que nos devuelva los proveedores
		
		//1ª MANERA
		RestTemplate plantillaPeticiones = new RestTemplate();
		//tenemos que sustituir las variables de la url
		Map<String,Integer> variablesUrl = new HashMap<String,Integer>();
		variablesUrl.put("id", id);
		plantillaPeticiones.delete("http://localhost:8080/proveedor/{id}",variablesUrl);
		//le pasamos todos los proveedores otra vez
		ProveedorBO[] proveedores = plantillaPeticiones.getForObject("http://localhost:8080/proveedores", ProveedorBO[].class);
		modelo.addAttribute("proveedores",proveedores);
		
		return "proveedores";
	}
}
