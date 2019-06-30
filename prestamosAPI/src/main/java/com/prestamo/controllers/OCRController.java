package com.prestamo.controllers;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OCRController {
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
}
