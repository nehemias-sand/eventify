package com.eventify.service;

import com.eventify.dao.ButacaDao;
import com.eventify.entity.Butaca;
import com.eventify.entity.Evento;
import com.eventify.entity.Instalacion;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Membreño & nehem
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
    
     public List<Butaca> findByInstalacion(Instalacion instalacion) {
        return butacaDao.findByInstalacion(instalacion);
    }
    
    public List<Butaca> findDisponiblesByEvento(Evento evento) {
        return butacaDao.findDisponiblesByEvento(evento);
    }
}
