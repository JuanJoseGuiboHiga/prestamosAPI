package com.prestamo.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.prestamo.connection.DBConnection;
import com.prestamo.entities.PropuestaPrestamo;
import com.prestamo.interfaces.PropuestaPrestamoDAOInterface;
import com.prestamo.rowmapper.PropuestaPrestamoRowMapper;

public class PropuestaPrestamoDAO implements PropuestaPrestamoDAOInterface{
	@Override
	public void registrarPropuestaPrestamo(PropuestaPrestamo propuesta) {
		  DBConnection conexion = DBConnection.getInstance();
		  JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();  
		  String sql = "INSERT INTO PropuestaPrestamo (IdSolicitud,Monto,Plazo,TasaInteres,Comentario,Estado) VALUES(?,?,?,?,?,?,?)";
		  jdbcTemplate.update(sql, new Object[] { propuesta.getIdSolicitud(),propuesta.getMonto(),propuesta.getPlazo(),
				  propuesta.getTasaInteres(),propuesta.getComentario(),propuesta.getEstado()});
	}

	@Override
	public List<PropuestaPrestamo> listarPropuestaPrestamo() {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
	     List<PropuestaPrestamo> propuestaPrestamo = jdbcTemplate.query("select * from PropuestaPrestamo", new PropuestaPrestamoRowMapper());
	     return propuestaPrestamo;
	}

	@Override
	public void actualizarPropuestaPrestamo(String idPropuesta, String estado) {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		 jdbcTemplate.update("update PropuestaPrestamo set Estado = ? where IdPropuesta = ?",estado ,idPropuesta);
	}

}
