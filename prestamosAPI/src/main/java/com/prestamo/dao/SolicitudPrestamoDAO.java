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

import com.prestamo.entities.SolicitudPrestamo;
import com.prestamo.interfaces.SolicitudPrestamoDAOInterface;
import com.prestamo.rowmapper.SolicitudPrestamoRowMapper;
import com.prestamo.connection.DBConnection;
public class SolicitudPrestamoDAO implements SolicitudPrestamoDAOInterface{
	@Override
	public List<SolicitudPrestamo> listarSolicitudesPrestamo() {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
	     List<SolicitudPrestamo> solicitudes = jdbcTemplate.query("select * from SolicitudPrestamo", new SolicitudPrestamoRowMapper());
	     System.out.println(solicitudes.get(0).getEstado().toString());
	     return solicitudes;
	}

	@Override
	public void registrarSolicitudPrestamo(SolicitudPrestamo solicitud) {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		  String sql = "INSERT INTO SolicitudPrestamo (IdSolicitante,Motivo,Monto,Estado, Activo, Pasivo, Patrimonio, Costo, VentaTotal,GastosAdministrativos,GastosVentas,MargenUtilidad,PDF) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		  KeyHolder holder = new GeneratedKeyHolder();
		  jdbcTemplate.update(new PreparedStatementCreator() {
		                  @Override
		                  public PreparedStatement createPreparedStatement(Connection connection)
		                          throws SQLException {
		                      PreparedStatement ps = connection.prepareStatement(sql.toString(),
		                    		  new String[] {"id"}); 
		                      ps.setInt(1, solicitud.getIdSolicitante());
		                      ps.setString(2, solicitud.getMotivo());
		                      ps.setDouble(3, solicitud.getMonto());
		                      ps.setString(4,solicitud.getEstado());
		                      ps.setDouble(5,solicitud.getActivo());
		                      ps.setDouble(6,solicitud.getPasivo());
		                      ps.setDouble(7,solicitud.getPatrimonio());
		                      ps.setDouble(8,solicitud.getCosto());
		                      ps.setDouble(9,solicitud.getVentaTotal());
		                      ps.setDouble(10,solicitud.getGastosAdministrativos());
		                      ps.setDouble(11,solicitud.getGastosVentas());
		                      ps.setDouble(12,solicitud.getMargenUtilidad());
		                      ps.setString(13, solicitud.getPdf());
		                      return ps;
		                  }
		              }, holder);
	}

	@Override
	public void actualizarEstadoSolicitud(String idSolicitud, String estado) {
		  DBConnection conexion = DBConnection.getInstance();
		  JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		  jdbcTemplate.update("update SolicitudPrestamo set Estado = ? where IdSolicitud = ?",estado ,idSolicitud);
	}
	
}
