package com.prestamo.controllers;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(path = "/registrarPrestamo", method = RequestMethod.GET)
	public @ResponseBody void registrarPrestamo() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PrestamoDAO prestamoDAO =(PrestamoDAO) context.getBean("prestamoDAO");
		context.getBean("conexion");
		Prestamo prestamo = new Prestamo();
		prestamo.setIdPrestamo(0);
		prestamo.setIdPropuesta(0);
		prestamo.setIdCliente(0);
		prestamo.setMonto(0.0);
		prestamo.setMotivo("");
		prestamo.setEstado("");
		prestamoDAO.registrarPrestamo(prestamo);
        context.close();
  }
}
