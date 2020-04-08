package com.dawes.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.dawes.modelo.GetUsuarioDTO;
import com.dawes.modelo.ProveedorBO;
import com.dawes.modelo.Usuario;
import com.dawes.servicios.UsuarioServicio;

@RequestMapping("/user")
@Controller
public class ControladorUsuarios {

	@Autowired
	private UsuarioServicio servicio;

	private RestTemplate plantillaPeticiones = new RestTemplate();

	@GetMapping("/inicio")
	public String inicio(Model modelo) {
		ProveedorBO[] proveedores = plantillaPeticiones.getForObject("http://localhost:8080/proveedores",
				ProveedorBO[].class);
		// le pasamos todos los atributos que necesite la plantilla de USUARIO
		modelo.addAttribute("proveedores", proveedores);
		modelo.addAttribute("proveedoractualizado", new ProveedorBO());// para actualizar

		return "proveedores";
	}

	@RequestMapping(value = "/actualizarProveedor")
	public String actualizar(@ModelAttribute("proveedoractualizado") ProveedorBO proveedor, Model modelo) {
		// en la url tenemos que poner el id de este proveedor
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		parametros.put("id", proveedor.getIdproveedor());
		plantillaPeticiones.put("http://localhost:8080/proveedor/{id}", proveedor, parametros);
		inicio(modelo);
		return "proveedoresAdmin";
	}

	@GetMapping("/usuario")
	public List<GetUsuarioDTO> mostrarUsuarios() {
		List<GetUsuarioDTO> lista = new ArrayList<GetUsuarioDTO>();
		for (Usuario usuario : servicio.findAll()) {
			lista.add(new GetUsuarioDTO(usuario));
		}
		return lista;
	}

	@PostMapping("/usuario")
	public void nuevoUsuario(@RequestBody Usuario nuevoUsuario) {
		servicio.save(servicio.crearNuevoUsuario(nuevoUsuario));
	}

	@GetMapping("/yo") // @Auth... nos devuelve las credenciales del usuario actualmente autenticado
	public GetUsuarioDTO yo(@AuthenticationPrincipal Usuario usuario) {
		return new GetUsuarioDTO(usuario);
	}
}
