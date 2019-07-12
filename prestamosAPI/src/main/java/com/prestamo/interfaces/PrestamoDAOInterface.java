package com.prestamo.interfaces;

import java.util.List;

import com.prestamo.entities.Prestamo;

public interface PrestamoDAOInterface {
    public int registrarPrestamo(Prestamo prestamo);
    public  List<Prestamo> listarPrestamo();
}
