package com.prestamo.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.prestamo.connection.DBConnection;
import com.prestamo.entities.Solicitante;
import com.prestamo.interfaces.SolicitanteDAOInterface;

public class SolicitanteDAO implements SolicitanteDAOInterface{

	@Override
	public void registrarSolicitante(Solicitante solicitante) {
		  DBConnection conexion = DBConnection.getInstance();
		  JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate(); 
		  String sql = "INSERT INTO Solicitante (IdSolicitante,Nombre,TipoDocumento,NumeroDocumento,Correo,Telefono) VALUES(?,?,?,?,?,?)";
		  jdbcTemplate.update(sql, new Object[] {solicitante.getIdSolicitante(),solicitante.getNombre(),solicitante.getTipoDocumento(),solicitante.getNumeroDocumento(),
				  solicitante.getCorreo(),solicitante.getTelefono()});
	}

}
