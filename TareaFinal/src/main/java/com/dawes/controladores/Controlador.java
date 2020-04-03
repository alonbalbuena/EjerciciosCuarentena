package com.dawes.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.dawes.modelo.ProveedorBO;
import com.dawes.servicios.ProveedorServicios;

@Controller
public class Controlador {

	@Autowired
	ProveedorServicios servicio;

	// Coje la ruta del inicio tras el login
	// pero devuelve otro html
	@RequestMapping("/inicio")
	public String inicio(Model modelo) {
		// generamos una peticion HTTP que nos devuelva los proveedores

		// 1ª MANERA
		RestTemplate plantillaPeticiones = new RestTemplate();
		// getForObject no permite enviar listas de objetos(podriamos crear un wrapper)
		// pero si arrays
		ProveedorBO[] proveedores = plantillaPeticiones.getForObject("http://localhost:8080/proveedores",
				ProveedorBO[].class);
		// le pasamos todos los proveedores
		modelo.addAttribute("proveedores", proveedores);
		//ademas de pasar un proveedor vacio que posteriormente rellenaremos con el formulario de creado.
		modelo.addAttribute("nuevoproveedor",new ProveedorBO());//para crear
		modelo.addAttribute("proveedoractualizar",new ProveedorBO());//para actualizar
		
		return "proveedores";
	}

	@RequestMapping("/eliminarProveedor")
	public String eliminar(Model modelo, @RequestParam("id") Integer id) {
		// generamos una peticion HTTP que nos devuelva los proveedores

		// 1ª MANERA
		RestTemplate plantillaPeticiones = new RestTemplate();
		// tenemos que sustituir las variables de la url
		Map<String, Integer> variablesUrl = new HashMap<String, Integer>();
		variablesUrl.put("id", id);
		plantillaPeticiones.delete("http://localhost:8080/proveedor/{id}", variablesUrl);
		// le pasamos todos los proveedores otra vez
		ProveedorBO[] proveedores = plantillaPeticiones.getForObject("http://localhost:8080/proveedores",
				ProveedorBO[].class);
		modelo.addAttribute("proveedores", proveedores);
		//ademas de pasar un proveedor vacio que posteriormente rellenaremos con el formulario de creado.
				modelo.addAttribute("nuevoproveedor",new ProveedorBO());

		return "proveedores";
	}

	@RequestMapping(value = "/crearProveedor",method = RequestMethod.POST)
	//request atribute determinadmos el tipo del objeto que hace la peticion al servidor(nombbre igual que el form)
	public String crear(@ModelAttribute("nuevoproveedor") ProveedorBO proveedorNuevo, Model modelo) {

		// generamos una peticion HTTP que nos devuelva los proveedores

		// 1ª MANERA
		RestTemplate plantillaPeticiones = new RestTemplate();
		//determinamos el servicio al que hacemos post, el objeto que pasamos y el tipo de la respuesta que esperamos
		String respuesta = plantillaPeticiones.postForObject("http://localhost:8080/proveedor", proveedorNuevo, String.class);
		// le pasamos todos los proveedores otra vez
		ProveedorBO[] proveedores = plantillaPeticiones.getForObject("http://localhost:8080/proveedores",
				ProveedorBO[].class);
		modelo.addAttribute("proveedores", proveedores);
		//ademas de pasar un proveedor vacio que posteriormente rellenaremos con el formulario de creado.
				modelo.addAttribute("nuevoproveedor",new ProveedorBO());
		System.out.println(respuesta);

		return "proveedores";
	}
}
