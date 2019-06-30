package com.prestamo.controllers;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalificarConversacionController {
	@RequestMapping(path = "/sql/{idUsuario}/{calificacion}/{comentario}", method = RequestMethod.GET)
	public @ResponseBody  String registrarCalificacion(@PathVariable String idUsuario
			,@PathVariable String calificacion,@PathVariable String comentario) {
		try {
			 comentario = URLDecoder.decode(comentario,"UTF-8");
			 insertarRegistro(idUsuario,calificacion,comentario);
		} catch (Exception e) {
			return e.toString();
		}
	    return "La calificacion se registro correctamente";
    }
	
  public  void insertarRegistro(String isUsuario, String calificacion, String comentario) {
		  
		  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
		  String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()).toString();
		  System.out.println(date);
	        String insertSql = "INSERT INTO ValoracionConversacion (Usuario, Calificacion, Comentario, FechaCalificacion)"
	                + "VALUES ('"+isUsuario+"', '"+calificacion+"', '"+comentario+"', '"+date+"');";

	        ResultSet resultSet = null;

	        try (Connection connection = DriverManager.getConnection(connectionUrl);
	                PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

	            prepsInsertProduct.execute();
	            // Retrieve the generated key from the insert.
	            resultSet = prepsInsertProduct.getGeneratedKeys();

	            // Print the ID of the inserted row.
	            while (resultSet.next()) {
	                System.out.println("Generated: " + resultSet.getString(1));
	            }
	        }
	        // Handle any errors that may have occurred.
	        catch (Exception e) {
	            e.printStackTrace();
	        }

	  }
}
