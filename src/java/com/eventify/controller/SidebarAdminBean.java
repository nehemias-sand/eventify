package com.eventify.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author nehem
 */
@Named(value = "sidebarAdminBean")
@ApplicationScoped
public class SidebarAdminBean {
    
    @Inject
    private LoginBean loginBean;

    private String currentPage = "dashboard";
    
    public String getSaludo() {
        return loginBean.getUsuarioSesion().get().getEmail().split("@")[0];
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
