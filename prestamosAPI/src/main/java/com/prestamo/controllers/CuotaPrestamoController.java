package com.prestamo.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.prestamo.dao.CuotaPrestamoDAO;
import com.prestamo.entities.CuotaPrestamo;

@Controller
public class CuotaPrestamoController {
	@RequestMapping(path = "/registrarCuota/{idCliente}/{idPrestamo}/{monto}/{plazo}", method = RequestMethod.GET)
	public @ResponseBody double registrarCuotas(@PathVariable int idCliente,@PathVariable int idPrestamo,@PathVariable double monto,@PathVariable String plazo) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CuotaPrestamoDAO cuotaPrestamo =(CuotaPrestamoDAO) context.getBean("cuotaDAO");
		context.getBean("conexion");
		double tea = 0.13;
		System.out.println(1+tea);
		System.out.println(1.0/12.0);
		double tem = (double) Math.pow(1+tea,1.0/12.0)-1;
		double item = (double) Math.pow(1+tem,12.0);
		System.out.println(tem);
		System.out.println(1+tem);
		System.out.println(item);
		double cuota = (tem*item)/((item)-1);
		CuotaPrestamo entidad = new CuotaPrestamo();
		entidad.setIdCliente(idCliente);
		entidad.setIdPrestamo(idPrestamo);
		entidad.setMonto(0);
		cuotaPrestamo.registrarCuota(entidad, Integer.parseInt(plazo),132.14);
        context.close();

        return cuota;
      
  }
}
