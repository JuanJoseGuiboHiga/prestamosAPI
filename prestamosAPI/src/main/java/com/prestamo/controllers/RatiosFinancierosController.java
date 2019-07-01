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
	@RequestMapping(path = "/generarRatios", method = RequestMethod.GET)
	public @ResponseBody RatiosFinancieros generarRatioFinanciero(@PathVariable String idSolicitud) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		SolicitudPrestamo solicitud = new SolicitudPrestamo();
		RatiosFinancierosDAO ratiosFinancierosDAO =(RatiosFinancierosDAO) context.getBean("solicitudPrestamoDAO");
		RatiosFinancieros ratios = ratiosFinancierosDAO.generarRatiosFinancieros(solicitud);
        context.close();
        return ratios;
   }
}
