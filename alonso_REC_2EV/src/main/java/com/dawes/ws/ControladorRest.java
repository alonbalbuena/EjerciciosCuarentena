package com.dawes.ws;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.modelos.EmpresaVO;
import com.dawes.modelos.GetDAOImpuesto;
import com.dawes.modelos.ImpuestosVO;
import com.dawes.servicios.EmpresasServicios;
import com.dawes.servicios.ImpuestosServicio;

@RestController
public class ControladorRest {
	
	@Autowired
	private ImpuestosServicio servicioImpuestos;
	
	@Autowired
	private EmpresasServicios servicioEmpresas;

	@GetMapping("/impuesto/{fecha1}/{fecha2}")
	public Set<GetDAOImpuesto> mostrarImpuestos(@PathVariable("fecha1") @DateTimeFormat(iso = ISO.DATE) LocalDate fechaInicial,
			@PathVariable("fecha2") @DateTimeFormat(iso = ISO.DATE) LocalDate fechaFinal) {
		
		/*obtenemos la lista de los impuestos*/
		Iterable<ImpuestosVO> listaImpuestos = servicioImpuestos.buscarEntreFechas(fechaInicial, fechaFinal);
		
		/*la transformamos a objetos DAO*/
		Set<GetDAOImpuesto> listaImpuestosDAO = new HashSet<GetDAOImpuesto>();
		for (ImpuestosVO impuesto : listaImpuestos) {
			/*cojemos las empresas que tengan dicho id y lo agregamos al DAO*/
			Optional<EmpresaVO> empresa = servicioEmpresas.findById(impuesto.getEmpresa().getIdempresa());
			listaImpuestosDAO.add(new GetDAOImpuesto(impuesto,empresa));
		}
		
		/*mostramos la lista con los objetos que queremos*/
		return listaImpuestosDAO;
	}
	 
}
