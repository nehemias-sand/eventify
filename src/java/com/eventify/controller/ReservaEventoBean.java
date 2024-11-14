package com.eventify.controller;

import com.eventify.entity.Butaca;
import com.eventify.entity.EstadoPago;
import com.eventify.entity.Evento;
import com.eventify.entity.Pago;
import com.eventify.entity.Reserva;
import com.eventify.entity.ReservaButaca;
import com.eventify.model.CategoriaButacas;
import com.eventify.service.ButacaService;
import com.eventify.service.EventoService;
import com.eventify.service.ReservaService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.TransferEvent;

/**
 *
 * @author nehem
 */
@Named(value = "reservaEventoBean")
@ViewScoped
public class ReservaEventoBean implements Serializable {

    @Inject
    private LoginBean loginBean;

    @Inject
    private ReservaService reservaService;

    @Inject
    private EventoService eventoService;

    @Inject
    private ButacaService butacaService;

    private List<Evento> proximosEventos;
    private Evento eventoSeleccionado;
    private List<CategoriaButacas> categoriasButacas;
    private BigDecimal totalReserva;

    @PostConstruct
    public void init() {
        proximosEventos = eventoService.findProximosEventos();
        totalReserva = BigDecimal.ZERO;
        categoriasButacas = new ArrayList<>();
    }

    public void onEventoSelect() {
        if (eventoSeleccionado != null) {
            List<Butaca> butacasDisponibles = butacaService.findDisponiblesByEvento(eventoSeleccionado);

            Map<String, List<Butaca>> butacasAgrupadas = butacasDisponibles.stream()
                    .collect(Collectors.groupingBy(b -> b.getIdCategoria().getNombre()));

            categoriasButacas = new ArrayList<>();
            for (Map.Entry<String, List<Butaca>> entry : butacasAgrupadas.entrySet()) {
                categoriasButacas.add(new CategoriaButacas(entry.getKey(), entry.getValue()));
            }
        }
        calcularTotal();
    }

    public void onTransfer(TransferEvent event) {
        calcularTotal();
    }

    private void calcularTotal() {
        try {
            totalReserva = categoriasButacas.stream()
                    .flatMap(categoria -> categoria.getButacas().getTarget().stream())
                    .map(butaca -> butaca.getIdCategoria().getPrecio())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reservar() {
        try {
            if (eventoSeleccionado != null && !getButacasSeleccionadas().isEmpty()) {
                Reserva reserva = new Reserva();
                reserva.setIdEvento(eventoSeleccionado);
                reserva.setFechaHora(new Date());
                reserva.setTotal(totalReserva);
                reserva.setIdCliente(loginBean.getUsuarioSesion().get().getCliente());

                List<ReservaButaca> reservaButacas = getButacasSeleccionadas().stream()
                        .map(butaca -> {
                            ReservaButaca rb = new ReservaButaca();
                            rb.setButaca(butaca);
                            rb.setReserva(reserva);
                            rb.setPrecio(butaca.getIdCategoria().getPrecio());
                            return rb;
                        })
                        .collect(Collectors.toList());

                Pago pago = new Pago();
                pago.setFechaHora(new Date());
                pago.setIdEstado(new EstadoPago(1));

                reservaService.crearReservaYPago(reserva, reservaButacas, pago);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Reserva Exitosa",
                                "Su reserva se ha realizado correctamente. Total: $" + totalReserva));

                // Reset selection
                eventoSeleccionado = null;
                categoriasButacas.clear();
                totalReserva = BigDecimal.ZERO;
                init();

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Advertencia",
                                "Por favor, seleccione un evento y al menos una butaca."));
            }
        } catch (Exception e) {
            e.printStackTrace();
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "Ocurrio un error al reservar"));
        }
    }

    // Getters and setters
    public List<Butaca> getButacasSeleccionadas() {
        return categoriasButacas.stream()
                .flatMap(categoria -> categoria.getButacas().getTarget().stream())
                .collect(Collectors.toList());
    }

    public List<Evento> getProximosEventos() {
        return proximosEventos;
    }

    public void setProximosEventos(List<Evento> proximosEventos) {
        this.proximosEventos = proximosEventos;
    }

    public Evento getEventoSeleccionado() {
        return eventoSeleccionado;
    }

    public void setEventoSeleccionado(Evento eventoSeleccionado) {
        this.eventoSeleccionado = eventoSeleccionado;
    }

    public List<CategoriaButacas> getCategoriasButacas() {
        return categoriasButacas;
    }

    public void setCategoriasButacas(List<CategoriaButacas> categoriasButacas) {
        this.categoriasButacas = categoriasButacas;
    }

    public BigDecimal getTotalReserva() {
        return totalReserva;
    }

    public void setTotalReserva(BigDecimal totalReserva) {
        this.totalReserva = totalReserva;
    }
}
