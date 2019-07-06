package com.prestamo.controllers;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prestamo.dao.RatiosFinancierosDAO;
import com.prestamo.entities.RatiosFinancieros;
import com.prestamo.entities.SolicitudPrestamo;

@Controller
public class RatiosFinancierosController {
	@RequestMapping(path = "/generarRatios/{activo}/{pasivo}/{patrimonio}/{costo}/{ventaTotal}/{gastosAdm}/{gastosVent}/{margenUti}", method = RequestMethod.GET)
	public @ResponseBody RatiosFinancieros generarRatioFinanciero(@PathVariable double activo,
			@PathVariable double pasivo,@PathVariable double patrimonio,@PathVariable double costo,@PathVariable double ventaTotal,@PathVariable double gastosAdm,@PathVariable double gastosVent, 
			@PathVariable double margenUti) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		SolicitudPrestamo solicitud = new SolicitudPrestamo();
		solicitud.setActivo(activo);
		solicitud.setPasivo(pasivo);
		solicitud.setPatrimonio(patrimonio);
		solicitud.setCosto(costo);
		solicitud.setVentaTotal(ventaTotal);
		solicitud.setGastosAdministrativos(gastosAdm);
		solicitud.setGastosVentas(gastosVent);
		solicitud.setMargenUtilidad(margenUti);
		RatiosFinancierosDAO ratiosFinancierosDAO =(RatiosFinancierosDAO) context.getBean("ratiosFinancierosDAO");
		RatiosFinancieros ratios = ratiosFinancierosDAO.generarRatiosFinancieros(solicitud);
        context.close();
        return ratios;
   }
}
