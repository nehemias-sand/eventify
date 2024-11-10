package com.eventify.controller;

import com.eventify.entity.Organizador;
import com.eventify.service.OrganizadorService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Membreño
 */
@Named(value = "organizadorBean")
@ViewScoped
public class OrganizadorBean implements Serializable{
    
    @Inject
    private OrganizadorService organizadorService;
    
    private List<Organizador> organizadores;
    private Organizador selectedOrganizador;
    
    @PostConstruct
    public void init() {
        organizadores = organizadorService.findAll();
        
        this.selectedOrganizador = new Organizador();
    }
    
    public void openNew() {
        this.selectedOrganizador = new Organizador();
    }
    
    public void save(){
        if(this.selectedOrganizador.getId() ==  null) {
            this.selectedOrganizador.setEstado(true);
            organizadorService.create(this.selectedOrganizador);
            organizadores.add(this.selectedOrganizador);
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Organizador registrado correctamente"));
            
            openNew();
        } else {
            organizadorService.update(this.selectedOrganizador);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Organizador actualizado correctamente"));
        }
    }
    
    public void changeEstado(int id) {
        organizadorService.changeEstado(id);
        organizadores = organizadorService.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estado de Organizador Cambiado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-organizadores");
    }

    public List<Organizador> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(List<Organizador> organizadores) {
        this.organizadores = organizadores;
    }

    public Organizador getSelectedOrganizador() {
        return selectedOrganizador;
    }

    public void setSelectedOrganizador(Organizador selectedOrganizador) {
        this.selectedOrganizador = selectedOrganizador;
    }
}
