package com.dawes.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
@Table(name = "proveedores")
public class ProveedorBO {

	@Id
	private int idproveedor;
	private String nombre;
	private String apellidos;
	private String nif;
	@DateTimeFormat(iso = ISO.DATE)//evitamos problemas en formularios al meter fechas
	private LocalDate fecha;
	public ProveedorBO(int idproveedor, String nombre, String apellidos, String nif, LocalDate fecha) {
		this.idproveedor = idproveedor;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nif = nif;
		this.fecha = fecha;
	}
	public ProveedorBO(String nombre, String apellidos, String nif, LocalDate fecha) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nif = nif;
		this.fecha = fecha;
	}
	public ProveedorBO() {
	}
	public Integer getIdproveedor() {
		return idproveedor;
	}
	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "ProveedorBO [idproveedor=" + idproveedor + ", nombre=" + nombre + ", apellidos=" + apellidos + ", nif="
				+ nif + ", fecha=" + fecha + "]";
	}
}
