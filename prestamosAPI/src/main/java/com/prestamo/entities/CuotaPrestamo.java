package com.prestamo.entities;

public class CuotaPrestamo {
	private String fechaVencimiento;

	public CuotaPrestamo(String fechaVencimiento) {
		super();
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
}
