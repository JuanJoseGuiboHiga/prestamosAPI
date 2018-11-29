package com.prestamo;

import java.io.File;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
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
import java.sql.PreparedStatement;
@Controller
public class Greeting {

	AIConfiguration configuration = new AIConfiguration("60d5fd9490c14a1a9f31fbf13814dbda");
	AIDataService dataService = new AIDataService(configuration);
	@RequestMapping(path = "/country", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Country> getCountry() {
	   Country country1 =  new Country("Peru", "Gran comida");
	   Country country2 =  new Country("Alemania", "aa");
	   ArrayList<Country> countries = new ArrayList<>();
	   countries.add(country1);
	   countries.add(country2);
	   return countries;
	}
	
	@RequestMapping(path = "/ocr", method = RequestMethod.GET)
	public @ResponseBody String getOCR() {
		 Tesseract tesseract = new Tesseract();
		 String text  = null;
		 String estadosFinancieros = null;
		 try {
			tesseract.setDatapath("D://Tesis/prestamosAPI/prestamosAPI/testdata");
			text = tesseract.doOCR(new File("D://Tesis/prestamosAPI/prestamosAPI/img/test.png"));
//	        Document doc = new Document(text);
//	        int i=0;
//	        for (Sentence sent : doc.sentences()) {
//	           if(sent.nerTag(i).equals("MONEY"))
//	           {
//	        	   estadosFinancieros = sent.word(i);
//	           }
//	        }
	        return text;
		 } catch (TesseractException e) {		
			e.printStackTrace();
		}
		return text;
	}
		
	@RequestMapping(path = "/nlp/{conversacion}", method = RequestMethod.GET)
		public @ResponseBody  ArrayList<RespuestaChatbot> getNLP(@PathVariable String conversacion) {
		  ArrayList<RespuestaChatbot> respuestas = new ArrayList<>();
		try {
	    	  AIRequest request = new AIRequest(conversacion);
	          AIResponse response = dataService.request(request);
	          RespuestaChatbot respuestaChat1 = null;
	          if (response.getStatus().getCode() == 200) {
	        	  respuestaChat1 = new RespuestaChatbot(response.getResult().getFulfillment().getSpeech());
	            } 
	   	   respuestas.add(respuestaChat1);
		 
		} catch (Exception e) {
			
		} 
		return respuestas;

	}
	
	@RequestMapping(path = "/sql", method = RequestMethod.GET)
	public @ResponseBody  void ejecutarQuery() {
	
	try {
		insertarRegistro();
	} catch (Exception e) {
		
	} 

}
	  public  void insertarRegistro() {
		  
		  String connectionUrl ="jdbc:sqlserver://;database=Coop;integratedSecurity=true;";

	        String insertSql = "INSERT INTO ValoracionConversacion (Usuario, Calificacion, Comentario, FechAcalificacion)"
	                + "VALUES ('US002', '6', 'Necesita ser mas amable', '2016-01-01');";

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