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

import com.prestamo.entities.Solicitante;
import com.prestamo.entities.SolicitudPrestamo;
import com.prestamo.interfaces.SolicitudPrestamoDAOInterface;
import com.prestamo.rowmapper.SolicitanteRowMapper;
import com.prestamo.rowmapper.SolicitudPrestamoRowMapper;
import com.prestamo.connection.DBConnection;
public class SolicitudPrestamoDAO implements SolicitudPrestamoDAOInterface{
	@Override
	public List<SolicitudPrestamo> listarSolicitudesPrestamo() {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
	     List<SolicitudPrestamo> solicitudes = jdbcTemplate.query("select * from SolicitudPrestamo where estado ='En Proceso' ", new SolicitudPrestamoRowMapper());
	     System.out.println(solicitudes.get(0).getEstado().toString());
	     return solicitudes;
	}
	

	@Override
	public void registrarSolicitudPrestamo(SolicitudPrestamo solicitud) {
	 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		  String sql = "INSERT INTO SolicitudPrestamo (IdSolicitante,Motivo,Monto,Plazo,Estado, Activo, Pasivo, Patrimonio, Costo, VentaTotal,GastosAdministrativos,GastosVentas,MargenUtilidad,PDF) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
		                      ps.setString(4, solicitud.getPlazo());
		                      ps.setString(5,solicitud.getEstado());
		                      ps.setDouble(6,solicitud.getActivo());
		                      ps.setDouble(7,solicitud.getPasivo());
		                      ps.setDouble(8,solicitud.getPatrimonio());
		                      ps.setDouble(9,solicitud.getCosto());
		                      ps.setDouble(10,solicitud.getVentaTotal());
		                      ps.setDouble(11,solicitud.getGastosAdministrativos());
		                      ps.setDouble(12,solicitud.getGastosVentas());
		                      ps.setDouble(13,solicitud.getMargenUtilidad());
		                      ps.setString(14, solicitud.getPdf());
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


	@Override
	public SolicitudPrestamo buscarSolicitud(int idPropuesta) {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		 String query = "Select SolicitudPrestamo.* from SolicitudPrestamo\r\n" + 
		 		"INNER JOIN PropuestaPrestamo\r\n" + 
		 		"ON SolicitudPrestamo.IdSolicitud = PropuestaPrestamo.IdSolicitud\r\n" + 
		 		"where IdPropuesta ="+idPropuesta+";";
		 SolicitudPrestamo solicitud = jdbcTemplate.queryForObject(query, new SolicitudPrestamoRowMapper());
	     return solicitud;
	}

}
