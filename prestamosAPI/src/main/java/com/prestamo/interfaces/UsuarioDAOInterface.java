package com.prestamo.interfaces;

public interface UsuarioDAOInterface {
	 public  String autenticarUsuario(String correo, String contrasenia);
	 public  int buscarUsuarioId(String correo);
}
