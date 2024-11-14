package com.eventify.dao;

import com.eventify.entity.Butaca;
import com.eventify.entity.Evento;
import com.eventify.entity.Instalacion;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Membreño & nehem
 */
public interface ButacaDao {   
    
    List<Butaca> findAll();
    
    Optional<Butaca> findById(int id);
    
    List<Butaca> findByIdReserva(int idReserva);
    
    List<Butaca> findByInstalacion(Instalacion instalacion);
    
    List<Butaca> findDisponiblesByEvento(Evento evento);
}
