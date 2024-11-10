package com.eventify.dao;

import com.eventify.entity.Butaca;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Membreño
 */
public interface ButacaDao {   
    
    List<Butaca> findAll();
    
    Optional<Butaca> findById(int id);
    
    List<Butaca> findByIdReserva(int idReserva);
}
