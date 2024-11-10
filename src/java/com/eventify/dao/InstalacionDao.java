package com.eventify.dao;

import java.util.List;
import java.util.Optional;

import com.eventify.entity.Instalacion;

/**
 *
 * @author Membreño
 */
public interface InstalacionDao {
    
    List<Instalacion> findAll();
    
    Optional<Instalacion> findById(int id);  
}
