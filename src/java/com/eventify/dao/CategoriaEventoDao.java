package com.eventify.dao;

import com.eventify.entity.CategoriaEvento;
import java.util.List;
/**
 *
 * @author Membreño
 */
public interface CategoriaEventoDao extends Dao<CategoriaEvento>{   
    
    public List<CategoriaEvento> findByEstadoActivo();
}
