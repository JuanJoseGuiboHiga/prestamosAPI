package com.prestamo.interfaces;

import java.util.List;
import java.util.Map;

import com.prestamo.entities.SolicitudPrestamo;

public interface SolicitudPrestamoDAOInterface {
    public void registrarSolicitudPrestamo(SolicitudPrestamo solicitud);
    public  List<SolicitudPrestamo> listarSolicitudesPrestamo();
    public  SolicitudPrestamo buscarSolicitud(int idPropuesta);
    public  SolicitudPrestamo buscarSolicitudPorId(int idSolicitud);
    public void actualizarEstadoSolicitud(String idSolicitud , String estado);
}
