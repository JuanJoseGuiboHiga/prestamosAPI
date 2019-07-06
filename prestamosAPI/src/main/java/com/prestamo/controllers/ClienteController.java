package com.prestamo.controllers;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.prestamo.dao.ClienteDAO;
import com.prestamo.entities.Cliente;

@Controller
public class ClienteController {
	
	@RequestMapping(path = "/registrarCliente/{nombre}/{tipodocumento}/{numerodocumento}/{correo}/{telefono}", method = RequestMethod.GET)
	public @ResponseBody int registrarCliente(@PathVariable String nombre, @PathVariable String tipodocumento,
			@PathVariable String numerodocumento,
			@PathVariable String correo,@PathVariable int telefono) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ClienteDAO clienteDAO =(ClienteDAO) context.getBean("clienteDAO");
		context.getBean("conexion");
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setTipoDocumento(tipodocumento);
		cliente.setNumeroDocumento(numerodocumento);
		cliente.setCorreo(correo);
		cliente.setTelefono(telefono);
		cliente.setEstado("");
		int key = clienteDAO.registrarCliente(cliente);
        context.close();
        return key;
  }
}
