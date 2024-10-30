package com.eventify.controller;

import com.eventify.entity.Usuario;
import com.eventify.service.UsuarioService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Optional;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author nehem
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    private String email;
    private String password;
    private Optional<Usuario> usuarioSesion = Optional.empty();

    public String login() {
        if (usuarioService.login(email, password)) {
            usuarioSesion = usuarioService.findByEmail(email);
            
            if (usuarioSesion.get().getIdRol().getNombre().equals("ADMIN")) {
                return "admin/panel?faces-redirect=true";
                
            } else {
                return "eventos/panel?faces-redirect=true";
            }
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de login", "Credenciales inválidas"));
            return null;
        }
    }

    public boolean isLoggedIn() {
        return usuarioSesion.isPresent();
    }

    public boolean hasRol(String rolNombre) {
        return isLoggedIn() && usuarioSesion.get().getIdRol().getNombre().equals(rolNombre);
    }

    public String logout() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSession(false) != null) {
            usuarioSesion = Optional.empty();
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return "/index.xhtml?faces-redirect=true";
        } else {
            return "/index.xhtml?faces-redirect=true";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Optional<Usuario> getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(Optional<Usuario> usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

}
