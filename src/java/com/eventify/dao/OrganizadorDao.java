package com.eventify.dao;

import com.eventify.entity.Organizador;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface OrganizadorDao extends Dao<Organizador>{
    
    public List<Organizador> findByEstadoActivo();
    
}
