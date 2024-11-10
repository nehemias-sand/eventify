package com.eventify.service;

import com.eventify.dao.OrganizadorDao;
import com.eventify.entity.Organizador;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Membreño
 */
@ApplicationScoped
public class OrganizadorService {
    
    @Inject
    private OrganizadorDao organizadorDao;
    
    public List<Organizador> findAll() {
        return organizadorDao.findAll();
    }
    
    public Optional<Organizador> findById(int id){
        return organizadorDao.findById(id);
    }
    
    public void create(Organizador organizador) {
        organizadorDao.save(organizador);
    }
    
    public void update(Organizador organizador) {
        organizadorDao.update(organizador);
    }
    
    public void delete(Organizador organizador) {
        organizadorDao.delete(organizador);
    }
    
    public void changeEstado(int id) {
        Optional<Organizador> organizadorOpt = findById(id);
        
        if (organizadorOpt.isPresent()) {
            Organizador organizador = organizadorOpt.get();
            organizador.setEstado(!organizador.getEstado());
            organizadorDao.update(organizador);
        }
    }
}
