package com.prestamo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.prestamo.connection.DBConnection;
import com.prestamo.entities.PropuestaPrestamo;
import com.prestamo.entities.Solicitante;
import com.prestamo.interfaces.PropuestaPrestamoDAOInterface;
import com.prestamo.rowmapper.PropuestaPrestamoRowMapper;
import com.prestamo.rowmapper.SolicitanteRowMapper;

public class PropuestaPrestamoDAO implements PropuestaPrestamoDAOInterface{
	@Override
	public void registrarPropuestaPrestamo(PropuestaPrestamo propuesta) {
		  DBConnection conexion = DBConnection.getInstance();
		  JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();  
		  String sql = "INSERT INTO PropuestaPrestamo (IdSolicitud,Monto,Plazo,TasaInteres,Comentario,Estado) VALUES(?,?,?,?,?,?)";
		  KeyHolder holder = new GeneratedKeyHolder();
		  jdbcTemplate.update(new PreparedStatementCreator() {
		                  @Override
		                  public PreparedStatement createPreparedStatement(Connection connection)
		                          throws SQLException {
		                      PreparedStatement ps = connection.prepareStatement(sql.toString(),
		                    		  new String[] {"id"}); 
		                      ps.setInt(1,  propuesta.getIdSolicitud());
		                      ps.setDouble(2, propuesta.getMonto());
		                      ps.setString(3, propuesta.getPlazo());
		                      ps.setString(4, propuesta.getTasaInteres());
		                      ps.setString(5,propuesta.getComentario());
		                      ps.setString(6,propuesta.getEstado());
		                      return ps;
		                  }
		              }, holder);
	}

	@Override
	public List<PropuestaPrestamo> listarPropuestaPrestamo() {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
	     List<PropuestaPrestamo> propuestaPrestamo = jdbcTemplate.query("select * from PropuestaPrestamo where estado ='Creada'", new PropuestaPrestamoRowMapper());
	     return propuestaPrestamo;
	}

	@Override
	public void actualizarPropuestaPrestamo(int idPropuesta, String estado) {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		 jdbcTemplate.update("update PropuestaPrestamo set Estado = ? where IdPropuesta = ?",estado ,idPropuesta);
	}



}
