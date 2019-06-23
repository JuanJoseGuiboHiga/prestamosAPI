package com.prestamo;

	public class SolicitudPrestamo {
	private String idSolicitud;
	  private int idSolicitante;
	  private String motivo;
	  private double monto;
	  private double activo;
	  private double pasivo;
	  private double patrimonio;
	  private double costo;
	  private double ventaTotal;
	  private double gastosAdministrativos;
	  private double gastosVentas;
	  private double margenUtilidad;
	  public SolicitudPrestamo(String estado, String idSolicitud, int idSolicitante, String motivo, double monto,
				double activo, double pasivo, double patrimonio, double costo, double ventaTotal,
				double gastosAdministrativos) {
			super();
			this.estado = estado;
			this.idSolicitud = idSolicitud;
			this.idSolicitante = idSolicitante;
			this.motivo = motivo;
			this.monto = monto;
			this.activo = activo;
			this.pasivo = pasivo;
			this.patrimonio = patrimonio;
			this.costo = costo;
			this.ventaTotal = ventaTotal;
			this.gastosAdministrativos = gastosAdministrativos;
			this.gastosVentas = gastosVentas;
			this.margenUtilidad = margenUtilidad;
		}
	public SolicitudPrestamo(String estado) {
		super();
		this.estado = estado;
	}



	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	private String estado;
	  public String getIdSolicitud() {
		return idSolicitud;
	}

	public int getIdSolicitante() {
		return idSolicitante;
	}

	public String getMotivo() {
		return motivo;
	}

	public double getMonto() {
		return monto;
	}

	public double getActivo() {
		return activo;
	}

	public double getPasivo() {
		return pasivo;
	}

	public double getPatrimonio() {
		return patrimonio;
	}

	public double getCosto() {
		return costo;
	}

	public double getVentaTotal() {
		return ventaTotal;
	}

	public double getGastosAdministrativos() {
		return gastosAdministrativos;
	}

	public double getGastosVentas() {
		return gastosVentas;
	}

	public double getMargenUtilidad() {
		return margenUtilidad;
	}
	
}
