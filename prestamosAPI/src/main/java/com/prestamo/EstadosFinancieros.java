package com.prestamo;

public class EstadosFinancieros {
	private double pasivo;
	private double activo;
	private double patrimonio;
	private double costo;
	private double ventasTotal;
	private double gastosAdministrativos;
	private double gastosVentas;
	private double gastosFinancieros;
	private double margenUtilidad;
	private double ventas;
	public EstadosFinancieros(double pasivo, double activo, double patrimonio, double costo, double ventasTotal,
			double gastosAdministrativos, double gastosVentas, double gastosFinancieros, double margenUtilidad,
			double ventas) {
		this.pasivo = pasivo;
		this.activo = activo;
		this.patrimonio = patrimonio;
		this.costo = costo;
		this.ventasTotal = ventasTotal;
		this.gastosAdministrativos = gastosAdministrativos;
		this.gastosVentas = gastosVentas;
		this.gastosFinancieros = gastosFinancieros;
		this.margenUtilidad = margenUtilidad;
		this.ventas = ventas;
	}
	public double getPasivo() {
		return pasivo;
	}
	public void setPasivo(double pasivo) {
		this.pasivo = pasivo;
	}
	public double getActivo() {
		return activo;
	}
	public void setActivo(double activo) {
		this.activo = activo;
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
	public double getVentasTotal() {
		return ventasTotal;
	}
	public void setVentasTotal(double ventasTotal) {
		this.ventasTotal = ventasTotal;
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
	public double getGastosFinancieros() {
		return gastosFinancieros;
	}
	public void setGastosFinancieros(double gastosFinancieros) {
		this.gastosFinancieros = gastosFinancieros;
	}
	public double getMargenUtilidad() {
		return margenUtilidad;
	}
	public void setMargenUtilidad(double margenUtilidad) {
		this.margenUtilidad = margenUtilidad;
	}
	public double getVentas() {
		return ventas;
	}
	public void setVentas(double ventas) {
		this.ventas = ventas;
	}
	
	
	
}
