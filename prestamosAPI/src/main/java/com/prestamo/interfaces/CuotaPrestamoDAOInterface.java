package com.prestamo.interfaces;
import com.prestamo.entities.CuotaPrestamo;

public interface CuotaPrestamoDAOInterface {
	 public void registrarCuota(CuotaPrestamo cuota, int plazo, double monto);
}
