package com.prestamo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.prestamo.entities.SolicitudPrestamo;

public class SolicitudPrestamoRowMapper2  implements RowMapper<SolicitudPrestamo> {

	@Override
	public SolicitudPrestamo mapRow(ResultSet rs, int rowNum) throws SQLException {
		 SolicitudPrestamo solicitud = new SolicitudPrestamo();
	        solicitud.setIdSolicitud(rs.getString("IdSolicitud"));
	        solicitud.setIdSolicitante(rs.getInt("IdSolicitante"));
	        solicitud.setMotivo(rs.getString("Motivo"));
	        solicitud.setNombre(rs.getString("Nombre"));
	        solicitud.setMonto(rs.getDouble("Monto"));
	        solicitud.setPlazo(rs.getString("Plazo"));
	        solicitud.setEstado(rs.getString("Estado"));
	        solicitud.setActivo(rs.getDouble("Activo"));
	        solicitud.setPasivo(rs.getDouble("Pasivo"));
	        solicitud.setPatrimonio(rs.getDouble("Patrimonio"));
	        solicitud.setCosto(rs.getDouble("Costo"));
	        solicitud.setVentaTotal(rs.getDouble("VentaTotal"));
	        solicitud.setGastosAdministrativos(rs.getDouble("GastosAdministrativos"));
	        solicitud.setGastosVentas(rs.getDouble("GastosVentas"));
	        solicitud.setMargenUtilidad(rs.getDouble("MargenUtilidad"));
	        solicitud.setPdf(rs.getString("PDF"));
	        return solicitud;
	}

}
