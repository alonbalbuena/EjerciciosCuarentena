package com.dawes.controladores;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.dawes.modelo.ProveedorBO;

@RequestMapping("/user")
@Controller
public class ControladorUsuarios {


	private RestTemplate plantillaPeticiones = new RestTemplate();

	@RequestMapping("/inicio")
	public String inicio(Model modelo) {
		ProveedorBO[] proveedores = plantillaPeticiones.getForObject("http://localhost:8080/proveedores",
				ProveedorBO[].class);
		// le pasamos todos los atributos que necesite la plantilla de USUARIO
		modelo.addAttribute("proveedores", proveedores);
		modelo.addAttribute("proveedoractualizado", new ProveedorBO());// para actualizar

		return "proveedoresUsuario";
	}

	@RequestMapping(value = "/actualizarProveedor")
	public String actualizar(@ModelAttribute("proveedoractualizado") ProveedorBO proveedor, Model modelo) {
		// en la url tenemos que poner el id de este proveedor
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		parametros.put("id", proveedor.getIdproveedor());
		plantillaPeticiones.put("http://localhost:8080/proveedor/{id}", proveedor, parametros);
		inicio(modelo);
		return "proveedoresUsuario";
	}
}
