package com.eventify.dao;

import java.util.List;
import com.eventify.entity.Reserva;
import com.eventify.entity.ReservaButaca;
import java.util.Optional;

/**
 *
 * @author Membreño & nehem
 */
public interface ReservaDao {
    
    public List<Reserva> findAll();
    
    public List<Reserva> findByDUIAndEvento(String dui, String evento);
    
    public Optional<Reserva> findById(int id);
    
    public List<Reserva> findReservasByCliente(int idCliente);
    
    void save(Reserva reserva, List<ReservaButaca> reservaButacas);
}
