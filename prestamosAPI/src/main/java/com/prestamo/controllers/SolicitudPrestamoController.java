package com.prestamo.controllers;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prestamo.dao.SolicitanteDAO;
import com.prestamo.dao.SolicitudPrestamoDAO;
import com.prestamo.entities.Solicitante;
import com.prestamo.entities.SolicitudPrestamo;

@Controller
public class SolicitudPrestamoController {

	@RequestMapping(path = "/listadoSolicitudes", method = RequestMethod.GET)
	public @ResponseBody List<SolicitudPrestamo> listarSolicitudes() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		SolicitudPrestamoDAO solicitudDAO =(SolicitudPrestamoDAO) context.getBean("solicitudPrestamoDAO");
		List<SolicitudPrestamo> listado =solicitudDAO.listarSolicitudesPrestamo();
        context.close();
        return listado;
  }
	
	@RequestMapping(path = "/registrarSolPres/{idSolicitante}/{motivo}/{monto}/{plazo}/{activo}/{pasivo}/{patrimonio}/{costo}/{ventaTotal}/{gastosAdm}/{gastosVent}/{margenUti}/{pdf}", method = RequestMethod.GET)
	public @ResponseBody void registrarSolicitudPrestamo(@PathVariable int idSolicitante,@PathVariable String motivo,@PathVariable double monto,@PathVariable String plazo,@PathVariable double activo,
			@PathVariable double pasivo,@PathVariable double patrimonio,@PathVariable double costo,@PathVariable double ventaTotal,@PathVariable double gastosAdm,@PathVariable double gastosVent, 
			@PathVariable double margenUti, @PathVariable String pdf) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SolicitudPrestamoDAO solicitudDAO =(SolicitudPrestamoDAO) context.getBean("solicitudPrestamoDAO");
		context.getBean("conexion");
		SolicitudPrestamo solicitud = new SolicitudPrestamo();
		solicitud.setIdSolicitante(idSolicitante);
		solicitud.setMotivo(motivo);
		solicitud.setMonto(monto);
		solicitud.setPlazo(plazo);
		solicitud.setEstado("En Proceso");
		solicitud.setActivo(activo);
		solicitud.setPasivo(pasivo);
		solicitud.setPatrimonio(patrimonio);
		solicitud.setCosto(costo);
		solicitud.setVentaTotal(ventaTotal);
		solicitud.setGastosAdministrativos(gastosAdm);
		solicitud.setGastosVentas(gastosVent);
		solicitud.setMargenUtilidad(margenUti);
		solicitud.setPdf(pdf);
        solicitudDAO.registrarSolicitudPrestamo(solicitud);
        context.close();
  }
	
	@RequestMapping(path = "/buscarSolicitud/{idPropuesta}",
			method = RequestMethod.GET)
	public @ResponseBody SolicitudPrestamo buscarSolicitante(@PathVariable int idPropuesta) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SolicitudPrestamoDAO solicitudDAO =(SolicitudPrestamoDAO) context.getBean("solicitudPrestamoDAO");
		context.getBean("conexion");
		SolicitudPrestamo solicitud = solicitudDAO.buscarSolicitud(idPropuesta);
        context.close();
        return solicitud;
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
