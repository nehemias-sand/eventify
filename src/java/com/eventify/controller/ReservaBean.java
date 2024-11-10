package com.eventify.controller;

import com.eventify.service.ReservaService;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import com.eventify.entity.Reserva;
import com.eventify.entity.Evento;
import com.eventify.entity.Butaca;
import com.eventify.service.ButacaService;
import java.util.ArrayList;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Membreño
 */
@Named(value = "reservaBean")
@ViewScoped
public class ReservaBean implements Serializable{

    @Inject
    private ReservaService reservaService;
    
    @Inject
    private ButacaService butacaService;
    
    private List<Butaca> butacasSeleccionadas = new ArrayList<>();
    
    private List<Reserva> reservas;
    private Reserva selectedReserva;
    private Evento seletecedEvento;
    private String duiFilter;
    private String eventoFilter;
    
    @PostConstruct
    public void init() {
        reservas = reservaService.findAll();
        
        this.selectedReserva = new Reserva();
    }
    
    public void filtrarReservas(){
        reservas = reservaService.findByDUIandEvento(duiFilter, eventoFilter);
    }
    
    public void limpiarFiltros() {
        this.duiFilter = null;
        this.eventoFilter = null;
        reservas = reservaService.findAll();
    }
    
    public void cargarButacas(int idReserva) {
        Optional<Reserva> reservaOptional = reservaService.findById(idReserva);
        
        Reserva reserva = reservaOptional.get();
        
        this.butacasSeleccionadas = butacaService.findByIdReserva(idReserva);
        this.seletecedEvento = reserva.getIdEvento();
        PrimeFaces.current().ajax().update("form:dlgButacas");
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getDuiFilter() {
        return duiFilter;
    }

    public void setDuiFilter(String duiFilter) {
        this.duiFilter = duiFilter;
    }

    public String getEventoFilter() {
        return eventoFilter;
    }

    public void setEventoFilter(String eventoFilter) {
        this.eventoFilter = eventoFilter;
    }
    
    public Reserva getSelectedReserva() {
        return selectedReserva;
    }

    public void setSelectedReserva(Reserva selectedReserva) {
        this.selectedReserva = selectedReserva;
    }

    public Evento getSeletecedEvento() {
        return seletecedEvento;
    }

    public void setSeletecedEvento(Evento seletecedEvento) {
        this.seletecedEvento = seletecedEvento;
    }

    public List<Butaca> getButacasSeleccionadas() {
        return butacasSeleccionadas;
    }

    public void setButacasSeleccionadas(List<Butaca> butacasSeleccionadas) {
        this.butacasSeleccionadas = butacasSeleccionadas;
    }
}
