package com.eventify.service;

import com.eventify.dao.CatalogoDao;
import com.eventify.dao.CategoriaEventoDao;
import com.eventify.dao.EventoDao;
import com.eventify.dao.InstalacionDao;
import com.eventify.dao.OrganizadorDao;
import com.eventify.entity.Butaca;
import com.eventify.entity.Evento;
import com.eventify.entity.Instalacion;
import com.eventify.entity.Organizador;
import com.eventify.entity.CategoriaEvento;
import com.eventify.entity.EstadoEvento;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Membreño & nehem
 */
@ApplicationScoped
public class EventoService {

    @Inject
    private EventoDao eventoDao;

    @Inject
    private OrganizadorDao organizadorDao;

    @Inject
    private InstalacionDao instalacionDao;

    @Inject
    private CategoriaEventoDao categoriaEventoDao;

    @Inject
    private CatalogoDao catalogoDao;

    public List<Evento> findAll() {
        return eventoDao.findAll();
    }

    public Optional<Evento> findById(int id) {
        return eventoDao.findById(id);
    }

    public List<Evento> findByNombre(String nombre) {
        return eventoDao.findByNombre(nombre);
    }

    public void create(Evento evento) {
        eventoDao.save(evento);
    }

    public void update(Evento evento) {
        eventoDao.update(evento);
    }

    public void delete(Evento evento) {
        eventoDao.delete(evento);
    }

    public List<Organizador> findOrganizadoresActivos() {
        return organizadorDao.findByEstadoActivo();
    }

    public List<Instalacion> findAllInstalaciones() {
        return instalacionDao.findAll();
    }

    public List<CategoriaEvento> findCategoriasEventoActivos() {
        return categoriaEventoDao.findByEstadoActivo();
    }

    public List<EstadoEvento> findAllEstadosEvento() {
        return catalogoDao.findAllEstadosEvento();
    }

    public List<Evento> findProximosEventos() {
        return eventoDao.findProximosEventos();
    }
    
    public List<Butaca> findButacasDisponiblesByEvento(Evento evento) {
        return eventoDao.findButacasDisponiblesByEvento(evento);
    }

    public List<Butaca> findButacasOcupadasByEvento(Evento evento) {
        return eventoDao.findButacasOcupadasByEvento(evento);
    }
}
