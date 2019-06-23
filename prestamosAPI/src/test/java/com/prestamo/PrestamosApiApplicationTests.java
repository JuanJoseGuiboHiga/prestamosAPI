package com.prestamo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.get;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrestamosApiApplicationTests {

/*	@Test
	public void contextLoads() {
	}*/
	@BeforeClass
	public static void init() {
	   RestAssured.baseURI = "http://localhost";
	   RestAssured.port = 8080;
	}
	 
	@Test
	public void chatbotTest() {
	       get("/nlp/hola")
	        .then()
	        .body("respuesta", equalTo("Bienvenido, que desea?"));
	}
	
	@Test
	public void registrarCalificacionTest() {
	       get("/sql/69xfACXE9qc7LFFTPowkCO9ab733/5.0/test")
	        .then()
	        .body(containsString("La calificacion se registro correctamente"));
	}
	
	@Test
	public void conversacionNoManejadaTest() {
	       get("/conversacion/69xfACXE9qc7LFFTPowkCO9ab733/dataPrueba")
	        .then()
	        .body(containsString("ok"));
	}
	
	@Test
	public void estadoSolicitudTest() {
	       get("/estadoSolicitud/SOL001")
	        .then()
	        .body("estado", equalTo("Su Solicitud fue:Aprobado"));
	}
	
	@Test
	public void estadoSolicitudRechazadaTest() {
	       get("/estadoSolicitud/SOL003")
	        .then()
	        .body("estado", equalTo("Su Solicitud fue:Rechazado. El motivo fue: El préstamo fue cancelado ya que el solicitante tiene un mal score crediticio"));
	}
	
	@Test
	public void fechaVencimientoTest() {
	       get("/fechaVencimiento/89652489")
	        .then()
	        .body("fechaVencimiento", equalTo("2019/06/21"));
	}
}
