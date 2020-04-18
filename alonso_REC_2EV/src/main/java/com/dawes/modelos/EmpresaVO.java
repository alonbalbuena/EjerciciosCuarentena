package com.dawes.modelos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="empresas")
public class EmpresaVO {

	@Id @GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer idempresa;
	private String denominacion;
	@OneToMany(mappedBy = "empresa",fetch = FetchType.EAGER)
	private Set<ImpuestosVO> declaraciones;
	
	public EmpresaVO() {
		super();
	}

	public EmpresaVO(String denominacion, HashSet<ImpuestosVO> declariaciones) {
		super();
		this.denominacion = denominacion;
		this.declaraciones = declariaciones;
	}

	public EmpresaVO(Integer idempresa, String denominacion, HashSet<ImpuestosVO> declariaciones) {
		super();
		this.idempresa = idempresa;
		this.denominacion = denominacion;
		this.declaraciones = declariaciones;
	}

	public Integer getIdempresa() {
		return idempresa;
	}

	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	//para evitar recursion infinita en el servicio REST
	@JsonIgnore
	public Set<ImpuestosVO> getDeclariaciones() {
		return declaraciones;
	}

	public void setDeclariaciones(HashSet<ImpuestosVO> declariaciones) {
		this.declaraciones = declariaciones;
	}

	@Override
	public String toString() {
		return "EmpresaVO [idempresa=" + idempresa + ", denominacion=" + denominacion + ", declariaciones="
				+ declaraciones + "]";
	}
}
