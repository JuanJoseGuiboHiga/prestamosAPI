package com.prestamo.entities;

public class Solicitante {

	private int idSolicitante;
	private int idUsuario;
	private String nombre;
	private String tipoDocumento;
	private String numeroDocumento;
	private String correo;
	private int telefono;
	
	public Solicitante() {
		super();
	}
	
	public int getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(int idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
