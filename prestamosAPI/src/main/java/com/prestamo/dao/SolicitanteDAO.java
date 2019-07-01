package com.prestamo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.prestamo.connection.DBConnection;
import com.prestamo.entities.Solicitante;
import com.prestamo.interfaces.SolicitanteDAOInterface;

public class SolicitanteDAO implements SolicitanteDAOInterface{

	@Override
	public int registrarSolicitante(Solicitante solicitante) {
		  DBConnection conexion = DBConnection.getInstance();
		  JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate(); 
		  String sql = "INSERT INTO Solicitante (IdUsuario,Nombre,TipoDocumento,NumeroDocumento,Correo,Telefono) VALUES(?,?,?,?,?,?)";
		  KeyHolder holder = new GeneratedKeyHolder();

		  jdbcTemplate.update(new PreparedStatementCreator() {
		                  @Override
		                  public PreparedStatement createPreparedStatement(Connection connection)
		                          throws SQLException {
		                      PreparedStatement ps = connection.prepareStatement(sql.toString(),
		                    		  new String[] {"id"}); 
		                      ps.setInt(1, 1);
		                      ps.setString(2, solicitante.getNombre());
		                      ps.setString(3, solicitante.getTipoDocumento());
		                      ps.setString(4, solicitante.getNumeroDocumento());
		                      ps.setString(5,solicitante.getCorreo());
		                      ps.setInt(6,solicitante.getTelefono());
		                      return ps;
		                  }
		              }, holder);
		  Number k = holder.getKey();
		  return  k.intValue();
	}

}
