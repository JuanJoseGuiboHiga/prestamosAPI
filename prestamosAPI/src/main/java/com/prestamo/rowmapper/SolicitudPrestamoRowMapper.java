package com.prestamo.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.prestamo.entities.SolicitudPrestamo;

public class SolicitudPrestamoRowMapper  implements RowMapper<SolicitudPrestamo> {

	@Override
	public SolicitudPrestamo mapRow(ResultSet rs, int rowNum) throws SQLException {
        SolicitudPrestamo solicitud = new SolicitudPrestamo();
        System.out.println(rs.getString("Motivo"));
        solicitud.setIdSolicitud(rs.getString("IdSolicitud"));
        solicitud.setIdSolicitante(rs.getInt("IdSolicitante"));
        solicitud.setMotivo(rs.getString("Motivo"));
        solicitud.setMonto(rs.getDouble("Monto"));
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
