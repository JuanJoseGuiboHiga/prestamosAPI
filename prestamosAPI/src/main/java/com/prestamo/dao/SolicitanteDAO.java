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
import com.prestamo.rowmapper.SolicitanteRowMapper;

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
	
	@Override
	public Solicitante buscarSolicitante(int idPropuesta) {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		 String query = "Select Solicitante.IdSolicitante,Solicitante.IdUsuario, Solicitante.Nombre, Solicitante.TipoDocumento, Solicitante.NumeroDocumento,Solicitante.Correo,Solicitante.Telefono from Solicitante\r\n" + 
		 		"INNER JOIN SolicitudPrestamo\r\n" + 
		 		"ON Solicitante.IdSolicitante = SolicitudPrestamo.IdSolicitante\r\n" + 
		 		"INNER JOIN PropuestaPrestamo\r\n" + 
		 		"ON SolicitudPrestamo.IdSolicitud = PropuestaPrestamo.IdSolicitud\r\n" + 
		 		"where IdPropuesta ="+idPropuesta+";";
	     Solicitante solicitante = jdbcTemplate.queryForObject(query, new SolicitanteRowMapper());
	     return solicitante;
	}

	@Override
	public Solicitante devolverNombreSolicitante(int idSolicitante) {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		 String query = "select Nombre from Solicitante where IdSolicitante='"+idSolicitante+"';";
		 Solicitante solicitante = jdbcTemplate.queryForObject(query, new SolicitanteRowMapper());
	     return solicitante;
	}

}
