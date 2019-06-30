package com.prestamo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.prestamo.entities.Prestamo;

public class PrestamoRowMapper implements RowMapper<Prestamo> {

	@Override
	public Prestamo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Prestamo prestamo = new Prestamo();
		prestamo.setIdPrestamo(rs.getInt("IdPrestamo"));
		prestamo.setIdPropuesta(rs.getInt("IdPropuesta"));
		prestamo.setIdCliente(rs.getInt("IdCliente"));
		prestamo.setMonto(rs.getDouble("Monto"));
		prestamo.setMotivo(rs.getString("Motivo"));
		prestamo.setEstado(rs.getString("Estado"));
        return prestamo;
	}
}
