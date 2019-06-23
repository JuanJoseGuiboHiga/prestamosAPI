package com.prestamo;

public class PropuestaPrestamo {

	private int idPropuesta;
	private String idSolicitud;
	private double monto;
	private String plazo;
	private double tasaInteres;
	private String comentario;
	
	public PropuestaPrestamo(int idPropuesta, String idSolicitud, double monto, String plazo, double tasaInteres,
			String comentario) {
		super();
		this.idPropuesta = idPropuesta;
		this.idSolicitud = idSolicitud;
		this.monto = monto;
		this.plazo = plazo;
		this.tasaInteres = tasaInteres;
		this.comentario = comentario;
	}
	
	public int getIdPropuesta() {
		return idPropuesta;
	}

	public String getIdSolicitud() {
		return idSolicitud;
	}

	public double getMonto() {
		return monto;
	}

	public String getPlazo() {
		return plazo;
	}

	public double getTasaInteres() {
		return tasaInteres;
	}

	public String getComentario() {
		return comentario;
	}
	
}
