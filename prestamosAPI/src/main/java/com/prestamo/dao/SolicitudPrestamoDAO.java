package com.prestamo.dao;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

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
	     return solicitudes;
	}

	@Override
	public void registrarSolicitudPrestamo(SolicitudPrestamo solicitud) {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		  String sql = "INSERT INTO SolicitudPrestamo (IdSolicitud,IdSolicitante,Motivo,Monto,Estado, Activo, Pasivo, Patrimonio, Costo, VentaTotal,GastosAdministrativos,GastosVentas,MargenUtilidad,PDF) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	      jdbcTemplate.update(sql, new Object[] { 
		  		solicitud.getIdSolicitud(), solicitud.getIdSolicitante(),solicitud.getMotivo(),solicitud.getMonto(),solicitud.getEstado(),
		  		solicitud.getActivo(),solicitud.getPasivo(),solicitud.getPatrimonio(),solicitud.getCosto(),solicitud.getVentaTotal(),solicitud.getGastosAdministrativos(),
		  		solicitud.getGastosVentas(),solicitud.getMargenUtilidad(),solicitud.getPdf()});
	}

	@Override
	public void actualizarEstadoSolicitud(String idSolicitud, String estado) {
		  DBConnection conexion = DBConnection.getInstance();
		  JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		  jdbcTemplate.update("update SolicitudPrestamo set Estado = ? where IdSolicitud = ?",estado ,idSolicitud);
	}
	
}
