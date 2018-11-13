package com.prestamo;

import java.io.File;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
@Controller
public class Greeting {

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
	
	
	@RequestMapping(path = "/nlp", method = RequestMethod.GET)
	public @ResponseBody String getNLP() {
	    try {
	    	String text = null;
	       // Document doc = new Document("$1000 is in the sky");
	        Sentence sentence = new Sentence("Hola, quisiera pedir un préstamo ");
	        //for (Sentence sent : doc.sentences()) {
	        	//text = sent.nerTag(0);
	        //}
	        for(String word: sentence.words())
	        {
	        	if(word.contains("Hola"))
	        	{
	        		text = "Hola, en que podemos ayudarte?";
	        		break;
	        	}
	        }
	        return text;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	


}