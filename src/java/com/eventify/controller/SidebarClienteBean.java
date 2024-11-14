package com.eventify.controller;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author nehem
 */
@Named(value = "sidebarClienteBean")
@ApplicationScoped
public class SidebarClienteBean {

    @Inject
    private LoginBean loginBean;

    private String currentPage = "eventos";
    
    public String getSaludo() {
        return loginBean.getUsuarioSesion().get().getCliente().getNombres();
    }
    
    public String logout() {
        return loginBean.logout();
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
    
}
