package com.eventify.service;

import com.eventify.dao.ButacaDao;
import com.eventify.entity.Butaca;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Membreño
 */
@ApplicationScoped
public class ButacaService {
    
    @Inject
    private ButacaDao butacaDao;
    
    public List<Butaca> findAll() {
        return butacaDao.findAll();
    }
    
    public Optional<Butaca> findById(int id){
        return butacaDao.findById(id);
    }
    
    public List<Butaca> findByIdReserva(int idReserva) {
        return butacaDao.findByIdReserva(idReserva);
    }
}
