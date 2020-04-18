package com.dawes.controladores;

import java.time.LocalDate;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.dawes.modelos.GetDAOImpuesto;
import com.dawes.servicios.EmpresasServicios;
import com.dawes.servicios.ImpuestosServicio;

@Controller
public class Controlador {

	@Autowired
	private RestTemplate peticion;
	
	@Autowired
	private EmpresasServicios servicio;
	
	@Autowired
	private ImpuestosServicio servicioImpuestos;

	@RequestMapping("/")
	public String inicio() {
		return "indice";
	}
	
	@RequestMapping("/formularioFechas")
	public String formularioImpuestosPorFechas(){
		return "/formulario/formFechas";
	}

	@RequestMapping("/procesarFechas")
	public String procesarFormularioImpuestos(@RequestParam("fechaInicial") String fechaInicial,
			@RequestParam("fechaFinal") String fechaFinal,
			Model modelo) {
		GetDAOImpuesto[] impuestos = peticion.getForObject("http://localhost:8080/impuesto/" + fechaInicial+"/"+fechaFinal,GetDAOImpuesto[].class);
		modelo.addAttribute("impuestos", impuestos);
		return "/formulario/listaImpuestos";
	}
	
	@RequestMapping("/impuestosEmpresa")
	public String informacionDeEmpresa(@RequestParam("idempresa") Integer id,Model modelo) {
		modelo.addAttribute("empresas", servicio.findAll());
		
		/*obtenemos los impuestos de dicha empresa*/
		modelo.addAttribute("impuestos",servicioImpuestos.findByEmpresa(servicio.findById(id)));
		return "/listado/desplegable";
	}
	
	@RequestMapping("/informacionImpuesto")
	public String mostrarInfoInpuesto(@RequestParam("idimpuesto") Integer id,Model modelo) {
		modelo.addAttribute("impuestoParaModificar",servicioImpuestos.findById(id));
		return "/listado/infoImpuesto";
	}
	
	@RequestMapping("/modificarImpuesto")
	public String modificarInpuesto(@PathParam("idimpuesto") Integer id
			,@RequestParam("nuevaBase") Integer nuevaBase
			,@RequestParam("nuevaFecha") @DateTimeFormat(iso = ISO.DATE) LocalDate nuevaFecha
			,Model modelo) {
		servicioImpuestos.actualizarImpuesto(id, nuevaBase,nuevaFecha);
		
		modelo.addAttribute("impuestoParaModificar",servicioImpuestos.findById(id));
		return "/listado/infoImpuesto";
	}
	
}
