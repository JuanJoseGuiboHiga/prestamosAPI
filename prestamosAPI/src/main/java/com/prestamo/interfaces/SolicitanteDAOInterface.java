package com.prestamo.interfaces;
import com.prestamo.entities.Solicitante;

public interface SolicitanteDAOInterface {
    public int registrarSolicitante(Solicitante solicitante);
    public Solicitante buscarSolicitante(int idPropuesta);
    public Solicitante devolverNombreSolicitante(int idSolicitante);
}
