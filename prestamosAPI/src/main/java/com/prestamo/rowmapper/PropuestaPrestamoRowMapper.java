package com.prestamo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.prestamo.entities.PropuestaPrestamo;

public class PropuestaPrestamoRowMapper implements RowMapper<PropuestaPrestamo> {

	@Override
	public PropuestaPrestamo mapRow(ResultSet rs, int rowNum) throws SQLException {
		PropuestaPrestamo propuesta = new PropuestaPrestamo();
		propuesta.setIdPropuesta(rs.getInt("IdPropuesta"));
		propuesta.setIdSolicitud(rs.getString("IdSolicitud"));
		propuesta.setMonto(rs.getDouble("Monto"));
		propuesta.setPlazo(rs.getString("Plazo"));
		propuesta.setTasaInteres(rs.getString("TasaInteres"));
		propuesta.setComentario(rs.getString("Comentario"));
		propuesta.setEstado(rs.getString("Estado"));
        return propuesta;
	}
}
