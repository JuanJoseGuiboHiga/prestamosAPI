package com.prestamo.entities;

	public class SolicitudPrestamo {
	
	  private String idSolicitud;
	  private int idSolicitante;
	  private String motivo;
	  private double monto;
	  private String estado;
	  private double activo;
	  private double pasivo;
	  private double patrimonio;
	  private double costo;
	  private double ventaTotal;
	  private double gastosAdministrativos;
	  private double gastosVentas;
	  private double margenUtilidad;
	  private String pdf;
	  
	  public SolicitudPrestamo() {
			super();
		}

	public SolicitudPrestamo(String estado) {
		super();
		this.estado = estado;
	}


	  public String getIdSolicitud() {
			return idSolicitud;
		}

		public void setIdSolicitud(String idSolicitud) {
			this.idSolicitud = idSolicitud;
		}

		public int getIdSolicitante() {
			return idSolicitante;
		}

		public void setIdSolicitante(int idSolicitante) {
			this.idSolicitante = idSolicitante;
		}

		public String getMotivo() {
			return motivo;
		}

		public void setMotivo(String motivo) {
			this.motivo = motivo;
		}

		public double getMonto() {
			return monto;
		}

		public void setMonto(double monto) {
			this.monto = monto;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public double getActivo() {
			return activo;
		}

		public void setActivo(double activo) {
			this.activo = activo;
		}

		public double getPasivo() {
			return pasivo;
		}

		public void setPasivo(double pasivo) {
			this.pasivo = pasivo;
		}

		public double getPatrimonio() {
			return patrimonio;
		}

		public void setPatrimonio(double patrimonio) {
			this.patrimonio = patrimonio;
		}

		public double getCosto() {
			return costo;
		}

		public void setCosto(double costo) {
			this.costo = costo;
		}

		public double getVentaTotal() {
			return ventaTotal;
		}

		public void setVentaTotal(double ventaTotal) {
			this.ventaTotal = ventaTotal;
		}

		public double getGastosAdministrativos() {
			return gastosAdministrativos;
		}

		public void setGastosAdministrativos(double gastosAdministrativos) {
			this.gastosAdministrativos = gastosAdministrativos;
		}

		public double getGastosVentas() {
			return gastosVentas;
		}

		public void setGastosVentas(double gastosVentas) {
			this.gastosVentas = gastosVentas;
		}

		public double getMargenUtilidad() {
			return margenUtilidad;
		}

		public void setMargenUtilidad(double margenUtilidad) {
			this.margenUtilidad = margenUtilidad;
		}

		public String getPdf() {
			return pdf;
		}

		public void setPdf(String pdf) {
			this.pdf = pdf;
		}

}
