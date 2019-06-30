package com.prestamo.controllers;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prestamo.dao.SolicitudPrestamoDAO;
import com.prestamo.entities.SolicitudPrestamo;

@Controller
public class SolicitudPrestamoController {

	@RequestMapping(path = "/test/{id}", method = RequestMethod.GET)
	public @ResponseBody void test(@PathVariable String id) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		SolicitudPrestamoDAO solicitudDAO =(SolicitudPrestamoDAO) context.getBean("solicitudPrestamoDAO");
        solicitudDAO.listarSolicitudesPrestamo();
        context.close();
  }
	
	@RequestMapping(path = "/registrarTest", method = RequestMethod.GET)
	public @ResponseBody void registrarSolicitudPrestamo() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SolicitudPrestamoDAO solicitudDAO =(SolicitudPrestamoDAO) context.getBean("solicitudPrestamoDAO");
		context.getBean("conexion");
		SolicitudPrestamo solicitud = new SolicitudPrestamo();
		solicitud.setIdSolicitud("SOL004");
		solicitud.setIdSolicitante(1);
		solicitud.setMotivo("Credito Prueba");
		solicitud.setMonto(9000);
		solicitud.setEstado("En Proceso");
		solicitud.setActivo(100);
		solicitud.setPasivo(200);
		solicitud.setPatrimonio(300);
		solicitud.setCosto(400);
		solicitud.setVentaTotal(500);
		solicitud.setGastosAdministrativos(600);
		solicitud.setGastosVentas(700);
		solicitud.setMargenUtilidad(8000);
		solicitud.setPdf("pdf3");
        solicitudDAO.registrarSolicitudPrestamo(solicitud);
        context.close();
  }
	
	@RequestMapping(path = "/testestado/{idSolicitud}/{estado}", method = RequestMethod.GET)
	public @ResponseBody void actualizarEstadoSolicitud(@PathVariable String idSolicitud,@PathVariable String estado) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		SolicitudPrestamoDAO solicitudDAO =(SolicitudPrestamoDAO) context.getBean("solicitudPrestamoDAO");
        solicitudDAO.actualizarEstadoSolicitud(idSolicitud, estado);
        context.close();
   }
}
