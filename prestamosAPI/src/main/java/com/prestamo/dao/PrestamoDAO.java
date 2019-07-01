package com.prestamo.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.prestamo.connection.DBConnection;
import com.prestamo.entities.Prestamo;
import com.prestamo.interfaces.PrestamoDAOInterface;
import com.prestamo.rowmapper.PrestamoRowMapper;

public class PrestamoDAO implements PrestamoDAOInterface {

	@Override
	public void registrarPrestamo(Prestamo prestamo) {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate(); 
		 String sql = "INSERT INTO Prestamo (IdPropuesta,IdCliente,Monto,Motivo,Estado) VALUES(?,?,?,?,?,?)";
		 jdbcTemplate.update(sql, new Object[] { prestamo.getIdPropuesta(),prestamo.getIdCliente(),prestamo.getMonto(),
				 prestamo.getMotivo(),prestamo.getEstado()});
	}

	@Override
	public List<Prestamo> listarPrestamo() {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
	     List<Prestamo> prestamo = jdbcTemplate.query("select * from Prestamo", new PrestamoRowMapper());
	     return prestamo;
	}

}
