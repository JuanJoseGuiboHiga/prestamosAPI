package com.prestamo.controllers;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.prestamo.dao.PropuestaPrestamoDAO;
import com.prestamo.entities.PropuestaPrestamo;

@Controller
public class PropuestaPrestamoController {
	
	@RequestMapping(path = "/listarPropuestasPrestamos", method = RequestMethod.GET)
	public @ResponseBody void listarPropuestaPrestamo() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		PropuestaPrestamoDAO propuestaPrestamoDAO =(PropuestaPrestamoDAO) context.getBean("propuestaPrestamoDAO");
		propuestaPrestamoDAO.listarPropuestaPrestamo();
        context.close();
  }
	
	@RequestMapping(path = "/registrarPropuestaPrestamo", method = RequestMethod.GET)
	public @ResponseBody void registrarPropuestaPrestamo() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PropuestaPrestamoDAO propuestaPrestamoDAO =(PropuestaPrestamoDAO) context.getBean("propuestaPrestamoDAO");
		context.getBean("conexion");
		PropuestaPrestamo propuesta = new PropuestaPrestamo();
		propuesta.setIdPropuesta(0);
		propuesta.setIdSolicitud("");
		propuesta.setMonto(0.0);
		propuesta.setPlazo("");
		propuesta.setTasaInteres("");
		propuesta.setComentario("");
		propuesta.setEstado("");
		propuestaPrestamoDAO.registrarPropuestaPrestamo(propuesta);
        context.close();
  }
	
	@RequestMapping(path = "/actualizarPropuesta/{idSolicitud}/{estado}", method = RequestMethod.GET)
	public @ResponseBody void actualizarEstadoPropuesta(@PathVariable String idPropuesta,@PathVariable String estado) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		PropuestaPrestamoDAO propuestaPrestamoDAO =(PropuestaPrestamoDAO) context.getBean("propuestaPrestamoDAO");
		propuestaPrestamoDAO.actualizarPropuestaPrestamo(idPropuesta, estado);
        context.close();
   }
}
