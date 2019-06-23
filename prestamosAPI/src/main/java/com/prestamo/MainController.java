package com.prestamo;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
@Controller
public class MainController {

	AIConfiguration configuration = new AIConfiguration("");
	AIDataService dataService = new AIDataService(configuration);
	@RequestMapping(path = "/ocr", method = RequestMethod.GET)
	public @ResponseBody void getOCR() {

		 try {
			 final String TARGET_URL =
		                "https://vision.googleapis.com/v1/images:annotate?key=";
		 URL serverUrl = new URL(TARGET_URL);
		 URLConnection urlConnection = serverUrl.openConnection();
		 urlConnection.setDoOutput(true);
		 HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;
		 httpConnection.setRequestProperty("Content-Type", "application/json");
		 BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                 OutputStreamWriter(httpConnection.getOutputStream()));
			httpRequestBodyWriter.write
			 ("{\"requests\":  [{ \"features\":  [ {\"type\": \"TEXT_DETECTION\""
			 +"}], \"image\": {\"source\": { \"imageUri\":"
			 +" \"https://d33v4339jhl8k0.cloudfront.net/docs/assets/5a6a22fb0428632faf622ddb/images/5b05911d2c7d3a2f9011d3b2/file-zGAlGIoLFL.png\"}}}]}");
			httpRequestBodyWriter.close();
			String response = httpConnection.getResponseMessage();
			if (httpConnection.getInputStream() == null) {
			    System.out.println("No stream");
			    return;
			}

			Scanner httpResponseScanner = new Scanner (httpConnection.getInputStream());
			String resp = "";
			while (httpResponseScanner.hasNext()) {
			    String line = httpResponseScanner.nextLine();
			    resp += line;
			    System.out.println(line);  //  alternatively, print the line of response
			}
			httpResponseScanner.close();
	
		 } catch (Exception e) {		
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(path = "/arbolDecision", method = RequestMethod.GET)
	public @ResponseBody void getPy() {

		 try {
			 Process p = Runtime.getRuntime().exec("python C://Users/JuanJosé/Desktop/python/clasificador.py > nuevo.txt");
			 BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			 String s = null;
			 while ((s =  in.readLine()) != null) {
				    System.out.println(s);
				}
		 } catch (Exception e) {		
			e.printStackTrace();
		}
		
	}
		
	@RequestMapping(path = "/nlp/{conversacion}", method = RequestMethod.GET)
		public @ResponseBody  RespuestaChatbot getRespuestaChatbot(@PathVariable String conversacion) {
	      RespuestaChatbot respuestaChat = null;
        	AIResponse response;
			try {
				  conversacion = URLDecoder.decode(conversacion,"UTF-8");
				  AIRequest request = new AIRequest(conversacion);
				  response = dataService.request(request);
		          if (response.getStatus().getCode() == 200) {
		        	  respuestaChat = new RespuestaChatbot(response.getResult().getFulfillment()
		        			  .getSpeech());
		          } 
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (AIServiceException e) {
				e.printStackTrace();
			}
		    return respuestaChat;

	}
	
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
	

	@RequestMapping(path = "/conversacion/{idUsuario}/{comentario}", method = RequestMethod.GET)
	public @ResponseBody  String registrarConversacionNoManejada(@PathVariable String idUsuario,
			@PathVariable String comentario) {
	
	try {
		 String comentarioDecodificado = URLDecoder.decode(comentario,"UTF-8");
		 insertarConversacion(idUsuario,comentarioDecodificado);
	} catch (Exception e) {
		System.out.println(e);
	}
		return "ok";
	}
	
	@RequestMapping(path = "/estadoSolicitud/{numero}", method = RequestMethod.GET)
	public @ResponseBody  SolicitudPrestamo getEstadoSolicitud(@PathVariable String numero) {
	    String estado = obtenerEstadoSolicitud(numero);
	    SolicitudPrestamo solicitud = new SolicitudPrestamo(estado);
	    return solicitud;
    }
	
	
	@RequestMapping(path = "/fechaVencimiento/{idCliente}", method = RequestMethod.GET)
	public @ResponseBody CuotaPrestamo getFechaVencimientoCuota(@PathVariable String idCliente) {
	    String fechaVencimiento = obtenerFechaVencimientoCuota(idCliente);
	    CuotaPrestamo cuotaPrestamo = new CuotaPrestamo(fechaVencimiento);
	    return cuotaPrestamo;
    }
	
	  public  void insertarConversacion(String isUsuario, String conversacion) {
		  
		  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
		  String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		  System.out.println(date);
	        String insertSql = "INSERT INTO ConversacionNoManejada (Usuario, Conversacion, FechaConversacion)"
	                + "VALUES ('"+isUsuario+"','"+conversacion+"', '"+date+"');";

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
	  
	  
		@RequestMapping(path = "/solPrestamo/{idSolicitante}/{motivo}/{monto}/{activo}/{pasivo}/{patrimonio}/{costo}/{ventaTotal}/{gastosAdm}/{gastosVent}/{margenUti}/{pdf}", method = RequestMethod.GET)
		public @ResponseBody  String registarSolicitudPrestamo(@PathVariable int idSolicitante,@PathVariable String motivo,@PathVariable double monto,@PathVariable double activo,
				@PathVariable double pasivo,@PathVariable double patrimonio,@PathVariable double costo,@PathVariable double ventaTotal,@PathVariable double gastosAdm,@PathVariable double gastosVent, 
				@PathVariable double margenUti, @PathVariable String pdf) {
		
		try {
			insertarSolicitudPrestamo(idSolicitante,motivo, monto, activo, pasivo, patrimonio, costo,ventaTotal,gastosAdm,gastosVent,margenUti,pdf);
		} catch (Exception e) {
			System.out.println(e);
		}
			return "ok";
		}
	  
	  public  void insertarSolicitudPrestamo(int idSolicitante, String motivo, double monto, double activo,
			  double pasivo, double patrimonio, double costo, double ventaTotal, double gastosAdm, double gastosVent,
			  double margenUti, String pdf) {
		  
		  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
	        String insertSql = "INSERT INTO SolicitudPrestamo (IdSolicitante, Motivo, Monto, Estado, Activo, Pasivo, Patrimonio, Costo, VentaTotal,"
	        		+ "GastosAdministrativos,GastosVentas,MargenUtilidad,PDF)"
	                + "VALUES ('"+idSolicitante+"','"+motivo+"', '"+monto+"','"+"EnProceso"+"','"+activo+"','"+pasivo+"','"+patrimonio+"','"+costo+"','"+ventaTotal+"', '"+gastosAdm+"','"+gastosVent+"', '"+margenUti+"', '"+pdf+"');";

	        System.out.println(insertSql);
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
	  
 public  void insertarSolicitante(String nombre, String tipoDoc, String numDoc, String correo, String telefono) {
		  
		  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
	        String insertSql = "INSERT INTO Solicitante (Nombre, TipoDocumento, NumeroDocumento, Correo, Telefono)"
	                + "VALUES ('"+nombre+"', '"+tipoDoc+"', '"+numDoc+"', '"+correo+"', '"+telefono+"');";

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
 
 public  void insertarPrestamo(String idPropuesta, String idCliente, String monto, String motivo, String estado) {
	  
	  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
       String insertSql = "INSERT INTO Prestamo (IdPropuesta, IdCliente, Monto, Motivo, Estado)"
               + "VALUES ('"+idPropuesta+"', '"+idCliente+"', '"+monto+"', '"+motivo+"', '"+estado+"');";

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
 
 public  void generarRatiosFinancieros(double activo, double pasivo, double patrimonio, double costo, double gastosAdm, double ventaTotal, double gastosVenta, double gastosFinancieros, double margenUtilidad) {
	 double ratio1, ratio2, ratio3, ratio4, ratio5, ratio6, ratio7;
	 ratio1=activo/pasivo;
	 ratio2=pasivo/patrimonio;
	 ratio3=costo/ventaTotal;
	 ratio4=gastosAdm/ventaTotal;
	 ratio5=gastosVenta/ventaTotal;
	 ratio6=gastosFinancieros/ventaTotal;
	 ratio7=margenUtilidad/ventaTotal;
 }
 
 
 public  String cambiarEstadoSolicitud(String idSolicitud, String estado) {
	  
	  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
       String insertSql = "UPDATE SolicitudPrestamo "
               + "SET  Estado='"+estado+"' where IdSolicitud= '"+idSolicitud+"';";
       System.out.println(insertSql);
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
           return e.toString();
       }
       return "ok";
 }
 
	@RequestMapping(path = "/cambiarEstadoSolicitud/{idSolicitud}/{estado}", method = RequestMethod.GET)
	public @ResponseBody String actualizarEstadoSolicitud(@PathVariable String idSolicitud,@PathVariable String estado) {
	    String mensaje = cambiarEstadoSolicitud(idSolicitud,estado);
	    return mensaje;
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
  
  public  void obtenerConversacionSinRespuesta() {
	  
	  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
      String insertSql = "select * from ConversacionNoManejada";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql);) {

            // Retrieve the generated key from the insert.
            resultSet = prepsInsertProduct.executeQuery();
            // Print the ID of the inserted row.
            clearTheFile();
            File file = new File("C://Users//JuanJosé//.spyder-py3//testFile3.txt");
            
            //Create the file
            if (file.createNewFile()){
              System.out.println("File is created!");
            }else{
              System.out.println("File already exists.");
            }
            while (resultSet.next()) {
              FileWriter writer = new FileWriter(file,true);
              BufferedWriter bw = new BufferedWriter(writer);
              bw.write(resultSet.getString("Conversacion"));
              bw.newLine();
              bw.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

  }
  
  
  public  String obtenerEstadoSolicitud(String numero) {
	  
	  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
      String insertSql = "SELECT SolicitudPrestamo.Estado, PropuestaPrestamo.Comentario\r\n" + 
      		"FROM SolicitudPrestamo\r\n" + 
      		"INNER JOIN PropuestaPrestamo ON SolicitudPrestamo.IdSolicitud = PropuestaPrestamo.IdSolicitud\r\n" + 
      		"Where SolicitudPrestamo.IdSolicitud ='"+numero+"'";
      String estado = null;
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
            PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql);) {
            resultSet = prepsInsertProduct.executeQuery();
            while (resultSet.next()) {
            	if(resultSet.getString(1).contains("Aprobado"))
            	{
                	estado = "Su Solicitud fue:"+ resultSet.getString(1);
            	}else if(resultSet.getString(1).contains("Rechazado"))
            	{
                	estado = "Su Solicitud fue:"+ resultSet.getString(1)+". El motivo fue: "+resultSet.getString(2);
            	}else {
            		estado = "Su Solicitud se encuentra:"+ resultSet.getString(1);
            	}
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
         
        return estado;

  }
  
	@RequestMapping(path = "/listadoSolicitudes", method = RequestMethod.GET)
	public @ResponseBody  ArrayList<SolicitudPrestamo> obtenerListadoSolicitudes() {
		ArrayList<SolicitudPrestamo> solicitudes = obtenerListadoDeSolicitudesPrestamo();
	    return solicitudes;
}
  
  public  ArrayList<SolicitudPrestamo> obtenerListadoDeSolicitudesPrestamo() {
	  
	  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
      String insertSql = "SELECT*FROM SolicitudPrestamo";
      String estado = null;
      ResultSet resultSet = null;
      ArrayList<SolicitudPrestamo> solicitudes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
            PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql);) {
            resultSet = prepsInsertProduct.executeQuery();
            while (resultSet.next()) {
            	SolicitudPrestamo solicitud = new SolicitudPrestamo(resultSet.getString(5),resultSet.getString(1),resultSet.getInt(2),
            			resultSet.getString(3),resultSet.getDouble(4),resultSet.getDouble(6),resultSet.getDouble(7),resultSet.getDouble(8),
            			resultSet.getDouble(9),resultSet.getDouble(10),resultSet.getDouble(11));
            	solicitudes.add(solicitud);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
         
        return solicitudes;

  }
  
	@RequestMapping(path = "/listadoPropuestas", method = RequestMethod.GET)
	public @ResponseBody  ArrayList<PropuestaPrestamo> obtenerListadoPropuestasPrestamo() {
		ArrayList<PropuestaPrestamo> propuestas = obtenerListadoPropuestas();
	    return propuestas;
    }
	
  public  ArrayList<PropuestaPrestamo> obtenerListadoPropuestas() {
	  
	  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
      String insertSql = "SELECT*FROM PropuestaPrestamo";
      String estado = null;
      ResultSet resultSet = null;
      ArrayList<PropuestaPrestamo> propuestas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
            PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql);) {
            resultSet = prepsInsertProduct.executeQuery();
            while (resultSet.next()) {
            	PropuestaPrestamo propuesta = new PropuestaPrestamo(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),
            			resultSet.getString(4),resultSet.getDouble(5),resultSet.getString(6));
            	propuestas.add(propuesta);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
         
        return propuestas;

  }
  
  
	@RequestMapping(path = "/listadoPrestamos", method = RequestMethod.GET)
	public @ResponseBody  ArrayList<Prestamo> obtenerPrestamos() {
		ArrayList<Prestamo> prestamos = obtenerListadoPrestamos();
	    return prestamos;
  }
	
public  ArrayList<Prestamo> obtenerListadoPrestamos() {
	  
	  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
    String insertSql = "SELECT*FROM Prestamo";
    String estado = null;
    ResultSet resultSet = null;
    ArrayList<Prestamo> prestamos = new ArrayList<>();
      try (Connection connection = DriverManager.getConnection(connectionUrl);
          PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql);) {
          resultSet = prepsInsertProduct.executeQuery();
          while (resultSet.next()) {
        	  Prestamo prestamo = new Prestamo(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),
          			resultSet.getDouble(4),resultSet.getString(5),resultSet.getString(6));
        	  prestamos.add(prestamo);
          }
      }
      catch (Exception e) {
          e.printStackTrace();
      }
       
      return prestamos;

}
  
  public  String obtenerFechaVencimientoCuota(String idCliente) {
	  
	  String connectionUrl ="jdbc:sqlserver://JUANJO\\sqlpoc;database=Coop;integratedSecurity=true;";
      String insertSql = "SELECT CONVERT(VARCHAR(10),CuotaPrestamo.FechaVencimiento, 111) FROM CuotaPrestamo\r\n" + 
      		"INNER JOIN Prestamo ON CuotaPrestamo.IdPrestamo = Prestamo.IdPrestamo\r\n" + 
      		"INNER JOIN PropuestaPrestamo ON PropuestaPrestamo.IdPropuesta = Prestamo.IdPropuesta\r\n" + 
      		"INNER JOIN Solicitudprestamo ON Solicitudprestamo.IdSolicitud = PropuestaPrestamo.IdSolicitud\r\n" + 
      		"INNER JOIN Solicitante ON Solicitante.IdSolicitante = Solicitudprestamo.IdSolicitante\r\n" + 
      		"WHERE MONTH(FechaVencimiento) = MONTH(dateadd(dd, -1, GetDate()))\r\n" + 
      		"AND YEAR(FechaVencimiento) = YEAR(dateadd(dd, -1, GetDate()))\r\n" + 
      		"AND Solicitante.NumeroDocumento="+"'"+idCliente+"'";
      String fechaVencimieno = null;
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
            PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql);) {
            resultSet = prepsInsertProduct.executeQuery();
            while (resultSet.next()) {
            	fechaVencimieno = resultSet.getString(1);
            	System.out.println(fechaVencimieno);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return fechaVencimieno;

  }
	  
	  public void createFile()  throws IOException
	  {
		 obtenerConversacionSinRespuesta();
	  }
	  
	  public static void clearTheFile() {
		  try
		  {
		        FileWriter fwOb = new FileWriter("C://Users//JuanJosé//.spyder-py3//testFile3.txt", false); 
		        PrintWriter pwOb = new PrintWriter(fwOb, false);
		        pwOb.flush();
		        pwOb.close();
		        fwOb.close();
		  }catch(Exception e)
		  {
			  
		  }

	    }
}