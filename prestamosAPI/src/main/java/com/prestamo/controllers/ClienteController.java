package com.prestamo.controllers;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.prestamo.dao.ClienteDAO;
import com.prestamo.entities.Cliente;

@Controller
public class ClienteController {
	
	@RequestMapping(path = "/registrarCliente", method = RequestMethod.GET)
	public @ResponseBody void registrarCliente() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ClienteDAO clienteDAO =(ClienteDAO) context.getBean("clienteDAO");
		context.getBean("conexion");
		Cliente cliente = new Cliente();
		cliente.setIdCliente(0);
		cliente.setNombre("");
		cliente.setTipoDocumento("");
		cliente.setNumeroDocumento("");
		cliente.setDireccion("");
		cliente.setCorreo("");
		cliente.setTelefono(0);
		cliente.setEstado("");
		clienteDAO.registrarCliente(cliente);
        context.close();
  }
}
