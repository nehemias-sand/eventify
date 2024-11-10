package com.eventify.dao;

import com.eventify.entity.Evento;
import java.util.List;

/**
 *
 * @author Membreño
 */
public interface EventoDao extends Dao<Evento> {
    
    public List<Evento> findByNombre(String nombre);
    
}
