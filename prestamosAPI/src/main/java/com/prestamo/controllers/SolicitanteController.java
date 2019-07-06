package com.prestamo.controllers;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.prestamo.dao.SolicitanteDAO;
import com.prestamo.entities.Solicitante;

@Controller
public class SolicitanteController {
	
	@RequestMapping(path = "/registrarSolicitante/{nombre}/{tipodocumento}/{numerodocumento}/{correo}/{telefono}",
			method = RequestMethod.GET)
	public @ResponseBody int registrarSolicitante(@PathVariable String nombre, @PathVariable String tipodocumento,
			@PathVariable String numerodocumento,
			@PathVariable String correo,@PathVariable int telefono) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SolicitanteDAO solicitanteDAO =(SolicitanteDAO) context.getBean("solicitanteDAO");
		context.getBean("conexion");
		Solicitante solicitante = new Solicitante();
		solicitante.setNombre(nombre);
		solicitante.setTipoDocumento(tipodocumento);
		solicitante.setNumeroDocumento(numerodocumento);
		solicitante.setCorreo(correo);
		solicitante.setTelefono(telefono);
		int key = solicitanteDAO.registrarSolicitante(solicitante);
        context.close();
        return key;
  }
	
	@RequestMapping(path = "/buscarSolicitante/{idPropuesta}",
			method = RequestMethod.GET)
	public @ResponseBody Solicitante buscarSolicitante(@PathVariable int idPropuesta) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SolicitanteDAO solicitanteDAO =(SolicitanteDAO) context.getBean("solicitanteDAO");
		context.getBean("conexion");
		Solicitante solicitante = solicitanteDAO.buscarSolicitante(idPropuesta);
        context.close();
        return solicitante;
  }
	
	
}
