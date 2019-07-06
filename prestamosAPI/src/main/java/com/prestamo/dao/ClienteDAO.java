package com.prestamo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.prestamo.connection.DBConnection;
import com.prestamo.entities.Cliente;
import com.prestamo.interfaces.ClienteDAOInterface;

public class ClienteDAO implements ClienteDAOInterface{

	@Override
	public int registrarCliente(Cliente cliente) {
		  DBConnection conexion = DBConnection.getInstance();
		  JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate(); 
		  String sql = "INSERT INTO Cliente (Nombre,TipoDocumento,NumeroDocumento,Correo,Telefono,Estado) VALUES(?,?,?,?,?,?)";
		  KeyHolder holder = new GeneratedKeyHolder();
		  jdbcTemplate.update(new PreparedStatementCreator() {
		                  @Override
		                  public PreparedStatement createPreparedStatement(Connection connection)
		                          throws SQLException {
		                      PreparedStatement ps = connection.prepareStatement(sql.toString(),
		                    		  new String[] {"id"}); 
		                      ps.setString(1, cliente.getNombre());
		                      ps.setString(2,cliente.getTipoDocumento());
		                      ps.setString(3, cliente.getNumeroDocumento());
		                      ps.setString(4,cliente.getCorreo());
		                      ps.setInt(5,cliente.getTelefono());
		                      ps.setString(6,cliente.getEstado());
		                      return ps;
		                  }
		              }, holder);
		  Number k = holder.getKey();
		  return  k.intValue();
	}

}
