package com.prestamo.dao;
import org.springframework.jdbc.core.JdbcTemplate;

import com.prestamo.connection.DBConnection;
import com.prestamo.entities.Cliente;
import com.prestamo.interfaces.ClienteDAOInterface;

public class ClienteDAO implements ClienteDAOInterface{

	@Override
	public void registrarCliente(Cliente cliente) {
		  DBConnection conexion = DBConnection.getInstance();
		  JdbcTemplate jdbcTemplate = conexion.getJdbcTemplate(); 
		  String sql = "INSERT INTO Cliente (IdCliente,Nombre,TipoDocumento,NumeroDocumento,Direccion,Correo,Telefono,Estado) VALUES(?,?,?,?,?,?)";
		  jdbcTemplate.update(sql, new Object[] {cliente.getIdCliente(),cliente.getNombre(),cliente.getTipoDocumento(),cliente.getNumeroDocumento(),
				  cliente.getDireccion(),cliente.getCorreo(),cliente.getTelefono(),cliente.getEstado()});
		
	}

}
