package com.prestamo.controllers;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.prestamo.dao.SolicitanteDAO;
import com.prestamo.entities.Solicitante;

@Controller
public class SolicitanteController {
	
	@RequestMapping(path = "/registrarSolicitante", method = RequestMethod.GET)
	public @ResponseBody void registrarSolicitante() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SolicitanteDAO solicitanteDAO =(SolicitanteDAO) context.getBean("solicitanteDAO");
		context.getBean("conexion");
		Solicitante solicitante = new Solicitante();
		solicitante.setIdSolicitante(0);
		solicitante.setNombre("");
		solicitante.setTipoDocumento("");
		solicitante.setNumeroDocumento("");
		solicitante.setCorreo("");
		solicitante.setTelefono(0);
		solicitanteDAO.registrarSolicitante(solicitante);
        context.close();
  }
}
