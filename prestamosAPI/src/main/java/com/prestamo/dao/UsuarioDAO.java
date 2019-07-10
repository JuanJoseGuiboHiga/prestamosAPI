package com.prestamo.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.prestamo.connection.DBConnection;
import com.prestamo.entities.Usuario;
import com.prestamo.interfaces.UsuarioDAOInterface;
import com.prestamo.rowmapper.UsuarioRowMapper;

public class UsuarioDAO implements UsuarioDAOInterface{

	@Override
	public String autenticarUsuario(String correo, String contrasenia) {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		 String query = "select*from Usuario where correo='"+correo+"' and contrasenia="+contrasenia;
	     Usuario usuario = jdbcTemplate.queryForObject(query, new UsuarioRowMapper());
	     if(usuario.getCorreo().contains(""))
	     {
	    	 return "ok";
	     }else {
	    	 return "denegado";
	     }
	}

	@Override
	public int buscarUsuarioId(String correo) {
		 DBConnection conexion = DBConnection.getInstance();
		 JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate();
		 String query = "select*from Usuario where correo='"+correo+"'";
	     Usuario usuario = jdbcTemplate.queryForObject(query, new UsuarioRowMapper());
		return usuario.getIdUsuario();
	}

}
