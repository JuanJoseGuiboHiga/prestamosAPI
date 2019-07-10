package com.prestamo.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.jdbc.core.JdbcTemplate;

import com.prestamo.connection.DBConnection;
import com.prestamo.entities.CuotaPrestamo;
import com.prestamo.interfaces.CuotaPrestamoDAOInterface;

public class CuotaPrestamoDAO implements CuotaPrestamoDAOInterface {

	@Override
	public void registrarCuota(CuotaPrestamo cuota, int plazo, double monto) {
		 DBConnection conexion = DBConnection.getInstance();
		  JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate(); 
		  String sql = "INSERT INTO CuotaPrestamo (IdPrestamo,IdCliente,NumeroCuota,Monto,FechaVencimiento) VALUES(?,?,?,?,?)";
		  for(int i =1;i<=plazo;i++)
		  {
		      Calendar cal = GregorianCalendar.getInstance();
		      SimpleDateFormat df = new SimpleDateFormat("dd MMMM,yyyy");
		      Date currentMonth = new Date();
		      cal.setTime(currentMonth);
		      cal.add(Calendar.MONTH, i);
		      String nextMonthAsString = df.format(cal.getTime());
			  jdbcTemplate.update(sql, new Object[] {cuota.getIdPrestamo(),cuota.getIdCliente(),i,monto,
					  nextMonthAsString});
		  }
	}

}
