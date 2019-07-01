package com.prestamo.interfaces;

import com.prestamo.entities.RatiosFinancieros;
import com.prestamo.entities.SolicitudPrestamo;

public interface RatiosFinancierosDAOInterface {
	public RatiosFinancieros generarRatiosFinancieros(SolicitudPrestamo solicitud);
}
