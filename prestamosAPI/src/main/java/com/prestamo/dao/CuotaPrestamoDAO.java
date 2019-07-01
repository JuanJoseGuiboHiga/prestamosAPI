package com.prestamo.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.prestamo.connection.DBConnection;
import com.prestamo.entities.CuotaPrestamo;
import com.prestamo.interfaces.CuotaPrestamoDAOInterface;

public class CuotaPrestamoDAO implements CuotaPrestamoDAOInterface {

	@Override
	public void registrarCuota(CuotaPrestamo cuota) {
		 DBConnection conexion = DBConnection.getInstance();
		  JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate(); 
		  String sql = "INSERT INTO CuotaPrestamo (IdPrestamo,IdCliente,NumeroCuota,Monto,FechaVencimiento) VALUES(?,?,?,?,?,?)";
		  jdbcTemplate.update(sql, new Object[] {cuota.getIdPrestamo(),cuota.getNumeroCuota(),cuota.getMonto(),
				  cuota.getFechaVencimiento()});
		
	}

}
