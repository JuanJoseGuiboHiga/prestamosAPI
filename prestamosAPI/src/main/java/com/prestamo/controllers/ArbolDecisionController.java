package com.prestamo.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArbolDecisionController {
	@RequestMapping(path = "/arbolDecision/{monto}/{plazo}/{activo}/{pasivo}/{patrimonio}/{costo}/{ventaTotal}/{gastosAdm}/{gastosVent}/{margenUti}", method = RequestMethod.GET)
	public @ResponseBody String ejecutarArbol(@PathVariable double monto,@PathVariable String plazo,@PathVariable double activo,
			@PathVariable double pasivo,@PathVariable double patrimonio,@PathVariable double costo,@PathVariable double ventaTotal,@PathVariable double gastosAdm,@PathVariable double gastosVent, 
			@PathVariable double margenUti) {
		 String s = null;
		 String respuesta= null;
		 try {
			 clearTheFile();
			 obtenerHistorial();
			 registrarSolicitudEnCSV(monto,activo,pasivo,patrimonio,costo,ventaTotal,gastosAdm,gastosVent,margenUti,plazo);
			 Process p = Runtime.getRuntime().exec("python C://Users/JuanJosé/Desktop/python/clasificador.py > nuevo.txt");
			 BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

			 while ((s =  in.readLine()) != null) {
				 respuesta = s;
				}
		 } catch (Exception e) {		
			e.printStackTrace();
		}
		 if(respuesta.contains("Moroso"))
		 {
			 respuesta = "Mal Pagador";
		 }else
		 {
			 respuesta = "Buen Pagador";
		 }
		 return respuesta;
	}
	
	 public  void obtenerHistorial() {
		  
		  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
	      String insertSql = "Select SolicitudPrestamo.Motivo,SolicitudPrestamo.Monto,SolicitudPrestamo.Activo,SolicitudPrestamo.Pasivo,\r\n" + 
	      		"SolicitudPrestamo.Patrimonio, SolicitudPrestamo.Costo, SolicitudPrestamo.VentaTotal, SolicitudPrestamo.GastosAdministrativos,\r\n" + 
	      		"SolicitudPrestamo.GastosVentas,SolicitudPrestamo.MargenUtilidad, PropuestaPrestamo.Plazo, Cliente.Estado  from SolicitudPrestamo\r\n" + 
	      		"inner join PropuestaPrestamo\r\n" + 
	      		"on SolicitudPrestamo.IdSolicitud = PropuestaPrestamo.IdSolicitud\r\n" + 
	      		"inner join Prestamo\r\n" + 
	      		"On PropuestaPrestamo.IdPropuesta = Prestamo.IdPropuesta\r\n" + 
	      		"inner join Cliente\r\n" + 
	      		"On Prestamo.IdCliente = Cliente.IdCliente;";

	        ResultSet resultSet = null;

	        try (Connection connection = DriverManager.getConnection(connectionUrl);
	                PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql);) {

	            // Retrieve the generated key from the insert.
	            resultSet = prepsInsertProduct.executeQuery();
	            // Print the ID of the inserted row.
	            File file = new File("D://data/historico.csv");
	            
	            //Create the file
	            if (file.createNewFile()){
	              System.out.println("File is created!");
	            }else{
	              System.out.println("File already exists.");
	            }
	            FileWriter writer = new FileWriter(file,true);
	            BufferedWriter bw = new BufferedWriter(writer);
	              bw.write("Monto,");
	              bw.write("Activo,");
	              bw.write("Pasivo,");
	              bw.write("Patrimonio,");
	              bw.write("Costo,");
	              bw.write("VentaTotal,");
	              bw.write("GastosAdministrativos,");
	              bw.write("GastosVentas,");
	              bw.write("MargenUtilidad,");
	              bw.write("Plazo,");
	              bw.write("Estado");
	              bw.newLine();
	            while (resultSet.next()) {
	              bw.write(" "+resultSet.getString("Monto")+",");
	              bw.write(" "+resultSet.getString("Activo")+",");
	              bw.write(" "+resultSet.getString("Pasivo")+",");
	              bw.write(" "+resultSet.getString("Patrimonio")+",");
	              bw.write(" "+resultSet.getString("Costo")+",");
	              bw.write(" "+resultSet.getString("VentaTotal")+",");
	              bw.write(" "+resultSet.getString("GastosAdministrativos")+",");
	              bw.write(" "+resultSet.getString("GastosVentas")+",");
	              bw.write(" "+resultSet.getString("MargenUtilidad")+",");
	              bw.write(" "+resultSet.getString("Plazo")+",");
	              bw.write(" "+resultSet.getString("Estado"));
	              bw.newLine();
		
	            }
	            bw.close();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }

	  }
	 
	 public  void registrarSolicitudEnCSV( double monto, double activo, double pasivo, double patrimonio, double costo, double ventaTotal,
			 double gastosAdministrativos, double gastosVentas, double margenUtilidad, String plazo) {
	     	 File file = new File("D://data/solicitud.csv");
	            FileWriter writer;
				try {
					writer = new FileWriter(file,true);
					 BufferedWriter bw = new BufferedWriter(writer);
		              bw.write("Monto,");
		              bw.write("Activo,");
		              bw.write("Pasivo,");
		              bw.write("Patrimonio,");
		              bw.write("Costo,");
		              bw.write("VentaTotal,");
		              bw.write("GastosAdministrativos,");
		              bw.write("GastosVentas,");
		              bw.write("MargenUtilidad,");
		              bw.write("Plazo,");
		              bw.write("Estado");
		              bw.newLine();
		              bw.write(monto+",");
		              bw.write(activo+",");
		              bw.write(pasivo+",");
		              bw.write(patrimonio+",");
		              bw.write(costo+",");
		              bw.write(ventaTotal+",");
		              bw.write(gastosAdministrativos+",");
		              bw.write(gastosVentas+",");
		              bw.write(margenUtilidad+",");
		              bw.write(plazo+",");
		              bw.write(" ");
		            bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
	  }
	 
	  public static void clearTheFile() {
		  try
		  {
		        FileWriter fwOb = new FileWriter("D://data/historico.csv", false); 
		        PrintWriter pwOb = new PrintWriter(fwOb, false);
		        pwOb.flush();
		        pwOb.close();
		        fwOb.close();
		        FileWriter fwOb2 = new FileWriter("D://data/solicitud.csv", false); 
		        PrintWriter pwOb2 = new PrintWriter(fwOb2, false);
		        pwOb2.flush();
		        pwOb2.close();
		        pwOb2.close();
		  }catch(Exception e)
		  {
			  
		  }

	    }
	  
	
}
