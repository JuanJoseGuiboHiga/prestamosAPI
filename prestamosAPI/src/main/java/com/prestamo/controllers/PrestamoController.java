package com.prestamo.controllers;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.prestamo.dao.PrestamoDAO;
import com.prestamo.entities.Prestamo;

@Controller
public class PrestamoController {
	@RequestMapping(path = "/listarPrestamos", method = RequestMethod.GET)
	public @ResponseBody void listarPrestamos() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		PrestamoDAO prestamoDAO =(PrestamoDAO) context.getBean("prestamoDAO");
		prestamoDAO.listarPrestamo();
        context.close();
  }
	
	@RequestMapping(path = "/registrarPrestamo/{idPropuesta}/{idCliente}/{monto}/{motivo}", method = RequestMethod.GET)
	public @ResponseBody Prestamo registrarPrestamo(@PathVariable int idPropuesta,@PathVariable int idCliente,
			@PathVariable double monto,@PathVariable String motivo) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PrestamoDAO prestamoDAO =(PrestamoDAO) context.getBean("prestamoDAO");
		context.getBean("conexion");
		Prestamo prestamo = new Prestamo();
		prestamo.setIdPropuesta(idPropuesta);
		prestamo.setIdCliente(idCliente);
		prestamo.setMonto(monto);
		prestamo.setMotivo(motivo);
		prestamo.setEstado("Creado");
		int id = prestamoDAO.registrarPrestamo(prestamo);
		prestamo.setIdPrestamo(id);
        context.close();
        return prestamo;
  }
}
