package com.eventify.controller;

import com.eventify.entity.Cliente;
import com.eventify.entity.Genero;
import com.eventify.entity.Usuario;
import com.eventify.service.ClienteService;
import com.eventify.service.UsuarioService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author nehem
 */
@Named(value = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private ClienteService clienteService;

    private List<Cliente> clientes;
    private List<Genero> generos;
    private Cliente selectedCliente;

    @PostConstruct
    public void init() {
        clientes = clienteService.findAll();
        generos = clienteService.findAllGeneros();

        this.selectedCliente = new Cliente();
        this.selectedCliente.setIdGenero(new Genero());
        this.selectedCliente.setIdUsuario(new Usuario());
    }

    public void openNew() {
        this.selectedCliente = new Cliente();
        this.selectedCliente.setIdGenero(new Genero());
        this.selectedCliente.setIdUsuario(new Usuario());
    }
    
    public void openEditDialog(Cliente cliente) {
        this.selectedCliente = cliente;
    }

    public void save() {
        String currentPasword = this.selectedCliente.getIdUsuario().getPassword();
        this.selectedCliente.getIdUsuario().setPassword(usuarioService.hashPassword(currentPasword));

        if (this.selectedCliente.getId() == null) {
            this.selectedCliente.getIdUsuario().setEstado(true);

            clienteService.create(this.selectedCliente);
            clientes.add(this.selectedCliente);
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cliente registrado correctamente"));
            
            openNew();
        } else {
            clienteService.update(this.selectedCliente);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cliente actualizado correctamente"));
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

}
