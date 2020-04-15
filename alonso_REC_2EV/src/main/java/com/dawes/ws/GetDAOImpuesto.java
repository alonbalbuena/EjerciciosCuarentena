package com.dawes.ws;

import java.time.LocalDate;
import java.util.Optional;

import com.dawes.modelos.EmpresaVO;
import com.dawes.modelos.ImpuestosVO;

public class GetDAOImpuesto {

	private LocalDate fechaImpuesto;
	private Integer baseImponible;
	private String denominacion;
	
	public GetDAOImpuesto(ImpuestosVO impuesto, Optional<EmpresaVO> empresa) {
		super();
		this.fechaImpuesto = impuesto.getFecha();
		this.baseImponible = impuesto.getBaseimponible();
		this.denominacion = empresa.get().getDenominacion();
	}
	public LocalDate getFechaImpuesto() {
		return fechaImpuesto;
	}
	public void setFechaImpuesto(LocalDate fechaImpuesto) {
		this.fechaImpuesto = fechaImpuesto;
	}
	public Integer getBaseImponible() {
		return baseImponible;
	}
	public void setBaseImponible(Integer baseImponible) {
		this.baseImponible = baseImponible;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	
	
}
