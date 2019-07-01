package com.prestamo.dao;
import com.prestamo.entities.RatiosFinancieros;
import com.prestamo.entities.SolicitudPrestamo;
import com.prestamo.interfaces.RatiosFinancierosDAOInterface;

public class RatiosFinancierosDAO implements  RatiosFinancierosDAOInterface{

	@Override
	public RatiosFinancieros generarRatiosFinancieros(SolicitudPrestamo solicitud) {
		 RatiosFinancieros ratios = new RatiosFinancieros();
		 double ratio1=solicitud.getActivo()/solicitud.getPasivo();
		 double ratio2=solicitud.getPasivo()/solicitud.getPatrimonio();
		 double ratio3=solicitud.getCosto()/solicitud.getVentaTotal();
		 double ratio4=solicitud.getGastosAdministrativos()/solicitud.getVentaTotal();
		 double ratio5=solicitud.getGastosVentas()/solicitud.getVentaTotal();
		 double ratio6=solicitud.getMargenUtilidad()/solicitud.getVentaTotal();
		 ratios.setRatio1(ratio1);
		 ratios.setRatio2(ratio2);
		 ratios.setRatio3(ratio3);
		 ratios.setRatio4(ratio4);
		 ratios.setRatio5(ratio5);
		 ratios.setRatio6(ratio6);
		 return ratios;
	}

}
