package com.prestamo.interfaces;

import java.util.List;

import com.prestamo.entities.PropuestaPrestamo;
import com.prestamo.entities.Solicitante;

public interface PropuestaPrestamoDAOInterface {
    public void registrarPropuestaPrestamo(PropuestaPrestamo propuesta);
    public  List<PropuestaPrestamo> listarPropuestaPrestamo();
    public void actualizarPropuestaPrestamo(int idPropuesta , String estado);
}
