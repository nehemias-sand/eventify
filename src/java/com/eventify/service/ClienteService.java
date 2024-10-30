package com.eventify.service;

import com.eventify.dao.CatalogoDao;
import com.eventify.dao.ClienteDao;
import com.eventify.entity.Cliente;
import com.eventify.entity.Genero;
import com.eventify.entity.Rol;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author nehem
 */
@ApplicationScoped
public class ClienteService {
    
    @Inject
    private ClienteDao clienteDAO;
    
    @Inject
    private CatalogoDao catalogoDAO;
    
    public List<Cliente> findAll() {
        return clienteDAO.findAll();
    }
    
    public Optional<Cliente> findById(int id) {
        return clienteDAO.findById(id);
    }
    
    public void create(Cliente cliente) {
        Optional<Rol> rol  = catalogoDAO.findRolByNombre("CLIENT");
        cliente.getIdUsuario().setIdRol(rol.get());
        
        clienteDAO.save(cliente);
    }
    
    public void update(Cliente cliente) {
        clienteDAO.update(cliente);
    }
    
    public void delete(Cliente cliente) {
        clienteDAO.delete(cliente);
    }
    
    public List<Genero> findAllGeneros() {
        return catalogoDAO.findAllGeneros();
    }
}
