package com.prestamo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.prestamo.connection.DBConnection;
import com.prestamo.entities.Prestamo;
import com.prestamo.interfaces.PrestamoDAOInterface;
import com.prestamo.rowmapper.PrestamoRowMapper;

public class PrestamoDAO implements PrestamoDAOInterface {

	@Override
	public int registrarPrestamo(Prestamo prestamo) {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate(); 
		  KeyHolder holder = new GeneratedKeyHolder();
		 String sql = "INSERT INTO Prestamo (IdPropuesta,IdCliente,Monto,Motivo,Estado) VALUES(?,?,?,?,?)";
		  jdbcTemplate.update(new PreparedStatementCreator() {
              @Override
              public PreparedStatement createPreparedStatement(Connection connection)
                      throws SQLException {
                  PreparedStatement ps = connection.prepareStatement(sql.toString(),
                		  new String[] {"id"}); 
                  ps.setInt(1, prestamo.getIdPropuesta());
                  ps.setInt(2, prestamo.getIdCliente());
                  ps.setDouble(3, prestamo.getMonto());
                  ps.setString(4,prestamo.getMotivo());
                  ps.setString(5,prestamo.getEstado());
                  return ps;
              }
          }, holder);
		Number k = holder.getKey();
		return  k.intValue();
	}

	@Override
	public List<Prestamo> listarPrestamo() {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
	     List<Prestamo> prestamo = jdbcTemplate.query("select * from Prestamo", new PrestamoRowMapper());
	     return prestamo;
	}

}
