package com.prestamo.controllers;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.prestamo.dao.UsuarioDAO;

@Controller
public class UsuarioController {
	@RequestMapping(path = "/autenticarUsario/{correo}/{contrasenia}", method = RequestMethod.GET)
	public @ResponseBody String autenticarUsuario(@PathVariable String correo,@PathVariable String contrasenia) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		UsuarioDAO usuarioDAO =(UsuarioDAO) context.getBean("usuarioDAO");
		String respuesta = usuarioDAO.autenticarUsuario(correo, contrasenia);
        context.close();
        return respuesta;
  }
	
	@RequestMapping(path = "/devolverUsuarioId/{correo}", method = RequestMethod.GET)
	public @ResponseBody int devolverUsuarioId(@PathVariable String correo) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("conexion");
		UsuarioDAO usuarioDAO =(UsuarioDAO) context.getBean("usuarioDAO");
		int idUsuario = usuarioDAO.buscarUsuarioId(correo);
        context.close();
        return idUsuario;
  }
}
