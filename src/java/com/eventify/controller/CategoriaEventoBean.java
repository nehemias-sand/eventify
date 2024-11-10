package com.eventify.controller;

import com.eventify.service.CategoriaEventoService;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import com.eventify.entity.CategoriaEvento;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Membreño
 */
@Named(value = "categoriaEventoBean")
@ViewScoped
public class CategoriaEventoBean implements Serializable{
    
   @Inject 
   private CategoriaEventoService categoriaEventoService;
   
   private List<CategoriaEvento> categoriasEvento;
   private CategoriaEvento selectedCategoriaEvento;
   
   @PostConstruct
    public void init() {
        categoriasEvento = categoriaEventoService.findAll();
        
        this.selectedCategoriaEvento = new CategoriaEvento();
    }
    
    public void openNew() {
        this.selectedCategoriaEvento = new CategoriaEvento();
    }
    
    public void save(){
        if(this.selectedCategoriaEvento.getId() ==  null) {
            this.selectedCategoriaEvento.setEstado(true);
            categoriaEventoService.create(this.selectedCategoriaEvento);
            categoriasEvento.add(this.selectedCategoriaEvento);
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría de Evento registrada correctamente"));
            
            openNew();
        } else {
            categoriaEventoService.update(this.selectedCategoriaEvento);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría de Evento actualizada correctamente"));
        }
    }
    
    public void changeEstado(int id) {
        categoriaEventoService.changeEstado(id);
        categoriasEvento = categoriaEventoService.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estado de Categorías Cambiado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-categoriasEvento");
    }

    public List<CategoriaEvento> getCategoriasEvento() {
        return categoriasEvento;
    }

    public void setCategoriasEvento(List<CategoriaEvento> categoriasEvento) {
        this.categoriasEvento = categoriasEvento;
    }

    public CategoriaEvento getSelectedCategoriaEvento() {
        return selectedCategoriaEvento;
    }

    public void setSelectedCategoriaEvento(CategoriaEvento selectedCategoriaEvento) {
        this.selectedCategoriaEvento = selectedCategoriaEvento;
    }
 
}
