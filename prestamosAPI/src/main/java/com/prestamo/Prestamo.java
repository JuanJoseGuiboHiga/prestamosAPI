package com.prestamo;

public class Prestamo {



	private int idPrestamo;
	private int idPropuesta;
	private int idCliente;
	private double monto;
	private String motivo;
	private String estado;
	
	public Prestamo(int idPrestamo, int idPropuesta, int idCliente, double monto, String motivo, String estado) {
		super();
		this.idPrestamo = idPrestamo;
		this.idPropuesta = idPropuesta;
		this.idCliente = idCliente;
		this.monto = monto;
		this.motivo = motivo;
		this.estado = estado;
	}
	
	public int getIdPrestamo() {
		return idPrestamo;
	}

	public int getIdPropuesta() {
		return idPropuesta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public double getMonto() {
		return monto;
	}

	public String getMotivo() {
		return motivo;
	}

	public String getEstado() {
		return estado;
	}
}
