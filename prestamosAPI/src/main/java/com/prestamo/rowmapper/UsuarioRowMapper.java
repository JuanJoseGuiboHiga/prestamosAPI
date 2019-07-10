package com.prestamo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.prestamo.entities.Usuario;

public class UsuarioRowMapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		  Usuario usuario = new Usuario();
		  usuario.setIdUsuario(rs.getInt("IdUsuario"));
		  usuario.setCorreo(rs.getString("Correo"));
		  usuario.setContrasenia(rs.getString("Contrasenia"));
		  usuario.setRol(rs.getString("Rol"));
	      return usuario;
	}

}
