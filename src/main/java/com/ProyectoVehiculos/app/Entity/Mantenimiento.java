package com.ProyectoVehiculos.app.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "mantenimientos")
public class Mantenimiento{

	@Id
    private String id;
    private String vehiculoId;
    private String mecanicoId;
    private String fecha;
    private String comentarios;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehiculoId() {
		return vehiculoId;
	}
	public void setVehiculoId(String vehiculoId) {
		this.vehiculoId = vehiculoId;
	}
	public String getMecanicoId() {
		return mecanicoId;
	}
	public void setMecanicoId(String mecanicoId) {
		this.mecanicoId = mecanicoId;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
}
