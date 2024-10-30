package com.eventify.controller;

import com.eventify.entity.Usuario;
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
@Named(value = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    private List<Usuario> usuarios;
    private Usuario selectedUsuario;

    @PostConstruct
    public void init() {
        usuarios = usuarioService.findAll();
    }

    public void openNew() {
        this.selectedUsuario = new Usuario();
    }

    public void save() {
        String currentPasword = this.selectedUsuario.getPassword();
        this.selectedUsuario.setPassword(usuarioService.hashPassword(currentPasword));

        if (this.selectedUsuario.getId() == null) {
            this.selectedUsuario.setEstado(true);

            usuarioService.create(this.selectedUsuario);
            usuarios.add(this.selectedUsuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Creado"));
        } else {
            usuarioService.update(this.selectedUsuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Actualizado"));
        }

        PrimeFaces.current().executeScript("PF('manageUsuarioDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-usuarios");
    }

    public void changeEstado(int id) {
        usuarioService.changeEstado(id);
        usuarios = usuarioService.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estado de Usuario Cambiado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-usuarios");
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

}
