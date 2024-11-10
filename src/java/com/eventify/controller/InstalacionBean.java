package com.eventify.controller;

import com.eventify.service.InstalacionService;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import com.eventify.entity.Instalacion;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Membreño
 */
@Named(value = "instalacionBean")
@ViewScoped
public class InstalacionBean implements Serializable{

    @Inject
    private InstalacionService instalacionService;
    
    private List<Instalacion> instalaciones;
    private Instalacion selectedInstalacion;
    private String selectedCroquis;
    
    @PostConstruct
    public void init() {
        instalaciones = instalacionService.findAll();
        
        this.selectedInstalacion = new Instalacion();
    }
    
    public void verCroquis(String urlCroquis) {
        this.selectedCroquis = urlCroquis;
    }

    public List<Instalacion> getInstalaciones() {
        return instalaciones;
    }

    public void setInstalaciones(List<Instalacion> instalaciones) {
        this.instalaciones = instalaciones;
    }

    public Instalacion getSelectedInstalacion() {
        return selectedInstalacion;
    }

    public void setSelectedInstalacion(Instalacion selectedInstalacion) {
        this.selectedInstalacion = selectedInstalacion;
    }

    public String getSelectedCroquis() {
        return selectedCroquis;
    }

    public void setSelectedCroquis(String selectedCroquis) {
        this.selectedCroquis = selectedCroquis;
    }
    
    
    
}
