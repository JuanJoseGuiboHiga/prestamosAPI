package com.prestamo.entities;

public class PropuestaPrestamo {

	private int idPropuesta;
	private String idSolicitud;
	private double monto;
	private String plazo;
	private String tasaInteres;
	private String comentario;
	private String estado;
	
	public PropuestaPrestamo() {
		super();
	}
	
	public int getIdPropuesta() {
		return idPropuesta;
	}
	public void setIdPropuesta(int idPropuesta) {
		this.idPropuesta = idPropuesta;
	}
	public String getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	public String getTasaInteres() {
		return tasaInteres;
	}
	public void setTasaInteres(String tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
