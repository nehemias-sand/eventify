package com.eventify.service;

import com.eventify.dao.ReservaDao;
import java.util.List;
import javax.inject.Inject;
import com.eventify.entity.Reserva;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Membreño
 */
@ApplicationScoped
public class ReservaService {
    
    @Inject
    private ReservaDao reservaDao;
    
    public List<Reserva> findAll(){
        return reservaDao.findAll();
    }
    
    public List<Reserva> findByDUIandEvento(String dui, String evento){
        return reservaDao.findByDUIAndEvento(dui, evento);
    }
    
    public Optional<Reserva> findById(int id){
        return reservaDao.findById(id);
    }
    
}
