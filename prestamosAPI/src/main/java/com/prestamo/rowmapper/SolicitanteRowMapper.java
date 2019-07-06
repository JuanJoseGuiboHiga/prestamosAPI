package com.prestamo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.prestamo.entities.Solicitante;
public class SolicitanteRowMapper implements RowMapper<Solicitante> {

	@Override
	public Solicitante mapRow(ResultSet rs, int rowNum) throws SQLException {
	    Solicitante solicitante = new Solicitante();
        solicitante.setIdSolicitante(rs.getInt("IdSolicitante"));
        solicitante.setIdUsuario(rs.getInt("IdUsuario"));
        solicitante.setNombre(rs.getString("Nombre"));
        solicitante.setTipoDocumento(rs.getString("TipoDocumento"));
        solicitante.setNumeroDocumento(rs.getString("NumeroDocumento"));
        solicitante.setCorreo(rs.getString("Correo"));
        solicitante.setTelefono(rs.getInt("Telefono"));
        return solicitante;
	}

}
