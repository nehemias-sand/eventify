package com.eventify.controller;

import com.eventify.entity.Butaca;
import com.eventify.service.EventoService;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.eventify.entity.Evento;
import com.eventify.entity.Organizador;
import com.eventify.entity.CategoriaEvento;
import com.eventify.entity.EstadoEvento;
import com.eventify.entity.Instalacion;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Membreño
 */
@Named(value = "eventoBean")
@ViewScoped
public class EventoBean implements Serializable {

    @Inject
    EventoService eventoService;

    private List<Evento> eventos;
    private List<Organizador> organizadoresActivos;
    private List<Instalacion> instalaciones;
    private List<CategoriaEvento> categoriasEventoActivos;
    private List<EstadoEvento> estadosEvento;
    private List<Butaca> butacasDisponibles;
    private List<Butaca> butacasOcupadas;
    private Evento selectedEvento;
    private String nombreFilter;

    @PostConstruct
    public void init() {
        eventos = eventoService.findAll();
        organizadoresActivos = eventoService.findOrganizadoresActivos();
        instalaciones = eventoService.findAllInstalaciones();
        categoriasEventoActivos = eventoService.findCategoriasEventoActivos();
        estadosEvento = eventoService.findAllEstadosEvento();

        this.selectedEvento = new Evento();
        this.selectedEvento.setIdOrganizador(new Organizador());
        this.selectedEvento.setIdInstalalacion(new Instalacion());
        this.selectedEvento.setIdCategoria(new CategoriaEvento());
        this.selectedEvento.setIdEstado(new EstadoEvento());

        cargarEventos();
    }

    public void openNew() {
        this.selectedEvento = new Evento();

        this.selectedEvento.setIdOrganizador(new Organizador());
        this.selectedEvento.setIdInstalalacion(new Instalacion());
        this.selectedEvento.setIdCategoria(new CategoriaEvento());
        this.selectedEvento.setIdEstado(new EstadoEvento());
    }

    public void cargarEventos() {
        eventos = eventoService.findAll();
    }

    public void save() {
        if (this.selectedEvento.getId() == null) {

            EstadoEvento estadoVigente = new EstadoEvento();
            estadoVigente.setId(1);

            this.selectedEvento.setIdEstado(estadoVigente);

            eventoService.create(this.selectedEvento);
            eventos.add(this.selectedEvento);

            eventos = eventoService.findAll();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Evento registrado correctamente"));

            openNew();
        } else {
            eventoService.update(this.selectedEvento);
            eventos = eventoService.findAll();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Evento actualizado correctamente"));
        }

        cargarEventos();

        PrimeFaces.current().executeScript("PF('manageEventoDialog').hide()");
        PrimeFaces.current().ajax().update("mainForm:dt-eventos", "mainForm:messages");
        PrimeFaces.current().executeScript("window.location.reload();");
    }

    public void filtrarPorNombre() {
        eventos = eventoService.findByNombre(nombreFilter);
    }

    public void limpiarFiltros() {
        this.nombreFilter = null;
        cargarEventos();
    }

    public void cancelarEvento() {
        if (this.selectedEvento.getId() != null) {

            Optional<Evento> eventoOptional = eventoService.findById(this.selectedEvento.getId());

            if (eventoOptional.isPresent()) {
                Evento evento = eventoOptional.get();

                EstadoEvento estadoCancelado = new EstadoEvento();
                estadoCancelado.setId(2);

                evento.setIdEstado(estadoCancelado);

                eventoService.update(evento);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Evento cancelado correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encontró el evento para cancelar."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Evento no seleccionado."));
        }
    }

    public void activarEvento() {
        if (this.selectedEvento.getId() != null) {

            Optional<Evento> eventoOptional = eventoService.findById(this.selectedEvento.getId());

            if (eventoOptional.isPresent()) {
                Evento evento = eventoOptional.get();

                EstadoEvento estadoVigente = new EstadoEvento();
                estadoVigente.setId(1);

                evento.setIdEstado(estadoVigente);

                eventoService.update(evento);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Evento activado correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encontró el evento para activar."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Evento no seleccionado."));
        }
    }
    
     public void cargarButacas() {
        if (selectedEvento != null) {
            butacasDisponibles = eventoService.findButacasDisponiblesByEvento(selectedEvento);
            butacasOcupadas = eventoService.findButacasOcupadasByEvento(selectedEvento);
        }
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public Evento getSelectedEvento() {
        return selectedEvento;
    }

    public void setSelectedEvento(Evento selectedEvento) {
        this.selectedEvento = selectedEvento;
    }

    public List<Organizador> getOrganizadoresActivos() {
        return organizadoresActivos;
    }

    public void setOrganizadoresActivos(List<Organizador> organizadoresActivos) {
        this.organizadoresActivos = organizadoresActivos;
    }

    public List<Instalacion> getInstalaciones() {
        return instalaciones;
    }

    public void setInstalaciones(List<Instalacion> instalaciones) {
        this.instalaciones = instalaciones;
    }

    public List<CategoriaEvento> getCategoriasEventoActivos() {
        return categoriasEventoActivos;
    }

    public void setCategoriasEventoActivos(List<CategoriaEvento> categoriasEventoActivos) {
        this.categoriasEventoActivos = categoriasEventoActivos;
    }

    public List<EstadoEvento> getEstadosEvento() {
        return estadosEvento;
    }

    public void setEstadosEvento(List<EstadoEvento> estadosEvento) {
        this.estadosEvento = estadosEvento;
    }

    public String getNombreFilter() {
        return nombreFilter;
    }

    public void setNombreFilter(String nombreFilter) {
        this.nombreFilter = nombreFilter;
    }
    
    public List<Butaca> getButacasDisponibles() {
        return butacasDisponibles;
    }

    public void setButacasDisponibles(List<Butaca> butacasDisponibles) {
        this.butacasDisponibles = butacasDisponibles;
    }

    public List<Butaca> getButacasOcupadas() {
        return butacasOcupadas;
    }

    public void setButacasOcupadas(List<Butaca> butacasOcupadas) {
        this.butacasOcupadas = butacasOcupadas;
    }
}
