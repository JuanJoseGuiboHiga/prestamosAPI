package com.prestamo.interfaces;

import java.util.List;

import com.prestamo.entities.PropuestaPrestamo;

public interface PropuestaPrestamoDAOInterface {
    public void registrarPropuestaPrestamo(PropuestaPrestamo propuesta);
    public  List<PropuestaPrestamo> listarPropuestaPrestamo();
    public void actualizarPropuestaPrestamo(String idPropuesta , String estado);
}
