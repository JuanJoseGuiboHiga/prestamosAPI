package com.prestamo.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prestamo.entities.RespuestaChatbot;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

@Controller
public class NlpController {
	AIConfiguration configuration = new AIConfiguration("");
	AIDataService dataService = new AIDataService(configuration);
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
}
