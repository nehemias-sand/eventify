package com.eventify.service;

import com.eventify.dao.InstalacionDao;
import java.util.List;
import javax.inject.Inject;
import com.eventify.entity.Instalacion;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Membreño
 */
@ApplicationScoped
public class InstalacionService {
    
    @Inject
    private InstalacionDao instalacionDao;
    
    public List<Instalacion> findAll(){
        return instalacionDao.findAll();
    }
    
    public Optional<Instalacion> findById(int id){
        return instalacionDao.findById(id);
    }
    
}
