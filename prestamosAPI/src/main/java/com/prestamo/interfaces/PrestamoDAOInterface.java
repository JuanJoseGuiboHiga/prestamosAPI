package com.prestamo.interfaces;

import java.util.List;

import com.prestamo.entities.Prestamo;

public interface PrestamoDAOInterface {
    public void registrarPrestamo(Prestamo prestamo);
    public  List<Prestamo> listarPrestamo();
}
