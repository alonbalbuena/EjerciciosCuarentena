package com.dawes.ws;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.modelos.EmpresaVO;
import com.dawes.modelos.ImpuestosVO;
import com.dawes.servicios.EmpresasServicios;
import com.dawes.servicios.ImpuestosServicio;

@RestController
public class ControladorRest {
	
	@Autowired
	private ImpuestosServicio servicioImpuestos;
	
	@Autowired
	private EmpresasServicios servicioEmpresas;

	@GetMapping("/impuesto/{fecha}")
	public Set<GetDAOImpuesto> mostrarImpuestos(@PathVariable("fecha") LocalDate fecha) {
		/*obtenemos la lista de los impuestos*/
		Set<ImpuestosVO> listaImpuestos = servicioImpuestos.findByFecha(fecha);
		
		/*la transformamos a objetos DAO*/
		HashSet<GetDAOImpuesto> listaImpuestosDAO = new HashSet<GetDAOImpuesto>();
		for (ImpuestosVO impuesto : listaImpuestos) {
			/*cojemos las empresas que tengan dicho id y lo agregamos al DAO*/
			Optional<EmpresaVO> empresa = servicioEmpresas.findById(impuesto.getEmpresa().getIdempresa());
			listaImpuestosDAO.add(new GetDAOImpuesto(impuesto,empresa));
		}
		
		/*mostramos la lista con los objetos que queremos*/
		return listaImpuestosDAO;
	}
}
