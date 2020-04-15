package com.dawes.modelos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="impuestos")
public class ImpuestosVO {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idpimpuesto;
	private Integer baseimponible;
	private LocalDate fecha;
	@ManyToOne
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
		this.idpimpuesto = idpimpuesto;
		this.baseimponible = baseimponible;
		this.fecha = fecha;
		this.empresa = empresa;
	}

	public Integer getIdpimpuesto() {
		return idpimpuesto;
	}

	public void setIdpimpuesto(Integer idpimpuesto) {
		this.idpimpuesto = idpimpuesto;
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

	public EmpresaVO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "ImpuestosVO [idpimpuesto=" + idpimpuesto + ", baseimponible=" + baseimponible + ", fecha=" + fecha
				+ ", empresa=" + empresa + "]";
	}
}
