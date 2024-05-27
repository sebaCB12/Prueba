package com.ProyectoVehiculos.app.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "vehiculos")
public class Vehiculo {
	@Id
	private String id;
	private String modelo;
	private String placa;
	private String propietarioId;
	private List<Mantenimiento> mantenimiento;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getPropietarioId() {
		return propietarioId;
	}
	public void setPropietarioId(String propietarioId) {
		this.propietarioId = propietarioId;
	}
	public List<Mantenimiento> getMantenimiento() {
		return mantenimiento;
	}
	public void setMantenimiento(List<Mantenimiento> mantenimiento) {
		this.mantenimiento = mantenimiento;
	}
	
	
	
}
