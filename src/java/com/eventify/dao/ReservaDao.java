package com.eventify.dao;

import java.util.List;
import com.eventify.entity.Reserva;
import java.util.Optional;

/**
 *
 * @author Membreño
 */
public interface ReservaDao {
    
    public List<Reserva> findAll();
    
    public List<Reserva> findByDUIAndEvento(String dui, String evento);
    
    public Optional<Reserva> findById(int id);
}
