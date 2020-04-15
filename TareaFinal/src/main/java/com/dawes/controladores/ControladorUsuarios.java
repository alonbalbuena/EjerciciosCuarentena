package com.dawes.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.dawes.modelo.ProveedorBO;
import com.dawes.modelo.Usuario;

@RequestMapping("/user")
@Controller
public class ControladorUsuarios {

	@Autowired//inyeccion de dependencia esta en la clase de configuracion de seguridad
	private RestTemplate template;

	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String inicio(Model modelo, @AuthenticationPrincipal Usuario usuarioLogeado) {
		    ProveedorBO[] proveedores = template.getForObject("http://localhost:8080/proveedores", ProveedorBO[].class);
		    // le pasamos todos los atributos que necesite la plantilla de USUARIO
		    modelo.addAttribute("proveedores", proveedores);
		    modelo.addAttribute("proveedoractualizado", new ProveedorBO());// para actualizar
		    modelo.addAttribute("nombreUsuario", usuarioLogeado.getUsername());
		return "proveedoresUsuario";
	}

	@RequestMapping(value = "/actualizarProveedor")
	public String actualizar(@ModelAttribute("proveedoractualizado") ProveedorBO proveedor, Model modelo, @AuthenticationPrincipal Usuario usuarioLogeado) {
		template.put("http://localhost:8080/proveedor/"+proveedor.getIdproveedor(), proveedor);
		return inicio(modelo,usuarioLogeado);
	}
}
