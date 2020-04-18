package com.dawes.modelos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="impuestos")
public class ImpuestosVO {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idimpuesto;
	private Integer baseimponible;
	@DateTimeFormat(iso = ISO.DATE)//necesario especificar para coincidir con DATE de HTML
	private LocalDate fecha;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idempresa")
	private EmpresaVO empresa;
	
	public ImpuestosVO() {
		super();
	}

	public ImpuestosVO(Integer baseimponible, LocalDate fecha, EmpresaVO empresa) {
		super();
		this.baseimponible = baseimponible;
		this.fecha = fecha;
		this.empresa = empresa;
	}

	public ImpuestosVO(Integer idpimpuesto, Integer baseimponible, LocalDate fecha, EmpresaVO empresa) {
		super();
		this.idimpuesto = idpimpuesto;
		this.baseimponible = baseimponible;
		this.fecha = fecha;
		this.empresa = empresa;
	}

	public Integer getIdimpuesto() {
		return idimpuesto;
	}

	public void setIdimpuesto(Integer idpimpuesto) {
		this.idimpuesto = idpimpuesto;
	}

	public Integer getBaseimponible() {
		return baseimponible;
	}

	public void setBaseimponible(Integer baseimponible) {
		this.baseimponible = baseimponible;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	//para evitar recursion infinita en el servicio REST
	@JsonIgnore
	public EmpresaVO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "ImpuestosVO [idimpuesto=" + idimpuesto + ", baseimponible=" + baseimponible + ", fecha=" + fecha
				+ ", empresa=" + empresa + "]";
	}
}
