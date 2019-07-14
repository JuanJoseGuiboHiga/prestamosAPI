package com.prestamo.controllers;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.prestamo.dao.PropuestaPrestamoDAO;
import com.prestamo.entities.PropuestaPrestamo;
import com.prestamo.entities.SolicitudPrestamo;

@Controller
public class PropuestaPrestamoController {
	
	@RequestMapping(path = "/listarPropuestasPrestamos", method = RequestMethod.GET)
	public @ResponseBody List<PropuestaPrestamo> listarPropuestaPrestamo() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		PropuestaPrestamoDAO propuestaPrestamoDAO =(PropuestaPrestamoDAO) context.getBean("propuestaPrestamoDAO");
		List<PropuestaPrestamo> listado = propuestaPrestamoDAO.listarPropuestaPrestamo();
        context.close();
        return listado;
  }
	
	@RequestMapping(path = "/listarPropuestasPrestamosAprobados", method = RequestMethod.GET)
	public @ResponseBody List<PropuestaPrestamo> listarPropuestaPrestamoAprobados() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		PropuestaPrestamoDAO propuestaPrestamoDAO =(PropuestaPrestamoDAO) context.getBean("propuestaPrestamoDAO");
		List<PropuestaPrestamo> listado = propuestaPrestamoDAO.listarPropuestaPrestamoAprobada();
        context.close();
        return listado;
  }
	
	@RequestMapping(path = "/registrarPropuestaPrestamo/{idSolicitud}/{monto}/{plazo}/{tasaInteres}/{comentario}/{estado}", method = RequestMethod.GET)
	public @ResponseBody void registrarPropuestaPrestamo(@PathVariable int idSolicitud,@PathVariable double monto,@PathVariable String plazo,@PathVariable String tasaInteres,
			@PathVariable String comentario,@PathVariable String estado) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PropuestaPrestamoDAO propuestaPrestamoDAO =(PropuestaPrestamoDAO) context.getBean("propuestaPrestamoDAO");
		context.getBean("conexion");
		PropuestaPrestamo propuesta = new PropuestaPrestamo();
		propuesta.setIdSolicitud(idSolicitud);
		propuesta.setMonto(monto);
		propuesta.setPlazo(plazo);
		propuesta.setTasaInteres(tasaInteres);
		propuesta.setComentario(comentario);
		propuesta.setEstado(estado);
		propuestaPrestamoDAO.registrarPropuestaPrestamo(propuesta);
        context.close();
  }
	
	@RequestMapping(path = "/actualizarPropuesta/{idPropuesta}/{estado}", method = RequestMethod.GET)
	public @ResponseBody void actualizarEstadoPropuesta(@PathVariable int idPropuesta,@PathVariable String estado) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		System.out.println(estado);
		PropuestaPrestamoDAO propuestaPrestamoDAO =(PropuestaPrestamoDAO) context.getBean("propuestaPrestamoDAO");
		propuestaPrestamoDAO.actualizarPropuestaPrestamo(idPropuesta, estado);
        context.close();
   }
	
	@RequestMapping(path = "/tasaInteresRecomendada/{motivo}/{monto}", method = RequestMethod.GET)
	public @ResponseBody String devolverTasaInteresRecomendada(@PathVariable String motivo,@PathVariable double monto) {
		String tasaInteres = null;
		switch(motivo){
		  case "CapitalDeTrabajo":
			    if(monto>=50000  && monto<=40000000)
			    {
			    	tasaInteres ="13";
			    }
			    break;
			  case "CompraDeuda":
				if(monto>=50000  && monto<=40000000)
				{
				    tasaInteres ="12";
				}
			    break;
			  case "CreditoVehicular":
				if(monto>=5000  && monto<=50000)
				{
					tasaInteres ="18";
				}
				break;
			  case "CompraLocal":
					if(monto>=50000  && monto<=40000000)
					{
					    tasaInteres ="12";
					}
				    break;
			  case "CreditoHipotecario":
					if(monto>=5000  && monto<=50000)
					{
						tasaInteres ="11";
					}
				    break;
			  default:
				  tasaInteres ="14";
			}
		return tasaInteres;
  }
}
