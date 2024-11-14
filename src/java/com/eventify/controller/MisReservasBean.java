package com.eventify.controller;

import com.eventify.entity.Reserva;
import com.eventify.entity.ReservaButaca;
import com.eventify.service.ReservaService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@Named(value = "misReservasBean")
@ViewScoped
public class MisReservasBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private ReservaService reservaService;

    @Inject
    private LoginBean loginBean;

    private List<Reserva> misReservas;
    private Reserva reservaSeleccionada;
    private List<ReservaButaca> butacasReservadas;

    @PostConstruct
    public void init() {
        try {
            if (reservaService == null || loginBean == null) {
                throw new IllegalStateException("Dependencias no inyectadas correctamente");
            }
            cargarMisReservas();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar las reservas: " + e.getMessage()));
        }
    }

    public void cargarMisReservas() {
        if (loginBean.getUsuarioSesion().isPresent()) {
            int idCliente = loginBean.getUsuarioSesion().get().getCliente().getId();
            misReservas = reservaService.findReservasByCliente(idCliente);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "No hay sesión de usuario activa"));
        }
    }

    public void verDetallesReserva(Reserva reserva) {
        try {
            System.out.println("Procesando reserva ID: " + reserva.getId());
            this.reservaSeleccionada = reserva;
            this.butacasReservadas = reserva.getReservaButacaList();
            System.out.println("Butacas cargadas: " + this.butacasReservadas.size());
            
            PrimeFaces.current().ajax().update("reservasForm:detallesReservaDialog");
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los detalles: " + e.getMessage()));
        }
    }

    // Getters y setters
    public List<Reserva> getMisReservas() {
        return misReservas;
    }

    public void setMisReservas(List<Reserva> misReservas) {
        this.misReservas = misReservas;
    }

    public Reserva getReservaSeleccionada() {
        return reservaSeleccionada;
    }

    public void setReservaSeleccionada(Reserva reservaSeleccionada) {
        this.reservaSeleccionada = reservaSeleccionada;
    }

    public List<ReservaButaca> getButacasReservadas() {
        return butacasReservadas;
    }

    public void setButacasReservadas(List<ReservaButaca> butacasReservadas) {
        this.butacasReservadas = butacasReservadas;
    }
}