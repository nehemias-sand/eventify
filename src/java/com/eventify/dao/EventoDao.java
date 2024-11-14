package com.eventify.dao;

import com.eventify.entity.Butaca;
import com.eventify.entity.Evento;
import java.util.List;

/**
 *
 * @author Membreño & nehem
 */
public interface EventoDao extends Dao<Evento> {
    
    public List<Evento> findByNombre(String nombre);
    
    List<Evento> findProximosEventos();
    
    List<Butaca> findButacasDisponiblesByEvento(Evento evento);
    
    List<Butaca> findButacasOcupadasByEvento(Evento evento);
}
