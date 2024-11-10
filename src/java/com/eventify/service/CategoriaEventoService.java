package com.eventify.service;

import com.eventify.dao.CategoriaEventoDao;
import java.util.List;
import javax.inject.Inject;
import com.eventify.entity.CategoriaEvento;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Membreño
 */
@ApplicationScoped
public class CategoriaEventoService {
    
    @Inject
    private CategoriaEventoDao categoriaEventoDao;
    
    public List<CategoriaEvento> findAll(){
        return categoriaEventoDao.findAll();
    }
    
    public Optional<CategoriaEvento> findById(int id) {
        return categoriaEventoDao.findById(id);
    }
    
    public void create(CategoriaEvento categoriaEvento) {
        categoriaEventoDao.save(categoriaEvento);
    }
    
    public void update(CategoriaEvento categoriaEvento) {
        categoriaEventoDao.update(categoriaEvento);
    }
    
    public void delete(CategoriaEvento categoriaEvento) {
        categoriaEventoDao.delete(categoriaEvento);
    }
    
    public void changeEstado(int id) {
        Optional<CategoriaEvento> categerioEventoOpt = findById(id);
        
        if (categerioEventoOpt.isPresent()) {
            CategoriaEvento categoriaEvento = categerioEventoOpt.get();
            categoriaEvento.setEstado(!categoriaEvento.getEstado());
            categoriaEventoDao.update(categoriaEvento);
        }
    }
    
}
