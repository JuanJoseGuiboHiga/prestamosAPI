package com.prestamo;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Greeting {

//	@RequestMapping(path = "/greeting/{msg}", method = RequestMethod.GET)
//	public @ResponseBody String getGreeting(@PathVariable String msg) {
//	   return "Your msg is: "+msg;
//	}
//	
//	@RequestMapping(path = "/greeting", method = RequestMethod.GET)
//	public  String getGreeting() {
//	   return "Your msg is: ";
//	}
	
	@RequestMapping(path = "/country", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Country> getCountry() {
	   Country country1 =  new Country("Peru", "Gran comida");
	   Country country2 =  new Country("Alemania", "aa");
	   ArrayList<Country> countries = new ArrayList<>();
	   countries.add(country1);
	   countries.add(country2);
	   return countries;
	}

}