package com.eventify.service;

import com.eventify.dao.PagoDao;
import com.eventify.dao.ReservaDao;
import com.eventify.entity.Pago;
import java.util.List;
import javax.inject.Inject;
import com.eventify.entity.Reserva;
import com.eventify.entity.ReservaButaca;
import java.text.SimpleDateFormat;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Membreño & nehem
 */
@ApplicationScoped
public class ReservaService {

    @Inject
    private ReservaDao reservaDao;

    @Inject
    private PagoDao pagoDao;

    @Inject
    private EmailService emailService;

    public List<Reserva> findAll() {
        return reservaDao.findAll();
    }

    public List<Reserva> findByDUIandEvento(String dui, String evento) {
        return reservaDao.findByDUIAndEvento(dui, evento);
    }

    public Optional<Reserva> findById(int id) {
        return reservaDao.findById(id);
    }

    public List<Reserva> findReservasByCliente(int idCliente) {
        return reservaDao.findReservasByCliente(idCliente);
    }

    public void crearReservaYPago(Reserva reserva, List<ReservaButaca> reservaButacas, Pago pago) {
        reservaDao.save(reserva, reservaButacas);
        pago.setIdReserva(reserva);
        pagoDao.save(pago);
        
        String destinatario = reserva.getIdCliente().getIdUsuario().getEmail();
        String asunto = "Detalles de tu reserva #" + reserva.getId();
        String cuerpo = generarCuerpoCorreo(reserva, reservaButacas, pago);
        
        emailService.enviarCorreoHtml(destinatario, asunto, cuerpo);
    }

    private String generarCuerpoCorreo(Reserva reserva, List<ReservaButaca> reservaButacas, Pago pago) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        StringBuilder butacasHtml = new StringBuilder();
        reservaButacas.forEach((rb) -> {
            butacasHtml.append("<tr>")
                    .append("<td style='padding: 10px; border-bottom: 1px solid #ddd;'>").append(rb.getButaca().getCodigo()).append("</td>")
                    .append("<td style='padding: 10px; border-bottom: 1px solid #ddd;'>").append(rb.getButaca().getIdCategoria().getNombre()).append("</td>")
                    .append("<td style='padding: 10px; border-bottom: 1px solid #ddd;'>$").append(String.format("%.2f", rb.getPrecio())).append("</td>")
                    .append("</tr>");
        });

        return "<!DOCTYPE html>"
                + "<html lang='es'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Detalles de tu Reserva</title>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; max-width: 600px; margin: 0 auto; padding: 20px; }"
                + "h1, h2 { color: #2c3e50; }"
                + ".header { background-color: #3498db; color: white; padding: 20px; text-align: center; }"
                + ".content { background-color: #f9f9f9; padding: 20px; border-radius: 5px; }"
                + "table { width: 100%; border-collapse: collapse; margin-top: 20px; }"
                + "th { background-color: #3498db; color: white; padding: 10px; text-align: left; }"
                + ".footer { margin-top: 20px; text-align: center; font-size: 0.8em; color: #7f8c8d; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='header'>"
                + "<h1>¡Gracias por tu Reserva!</h1>"
                + "</div>"
                + "<div class='content'>"
                + "<h2>Detalles de la Reserva</h2>"
                + "<p><strong>ID de Reserva:</strong> " + reserva.getId() + "</p>"
                + "<p><strong>Evento:</strong> " + reserva.getIdEvento().getNombre() + "</p>"
                + "<p><strong>Fecha y Hora del Evento:</strong> " + sdf.format(reserva.getIdEvento().getFechaHora()) + "</p>"
                + "<p><strong>Total:</strong> $" + String.format("%.2f", reserva.getTotal()) + "</p>"
                + "<p><strong>Fecha y Hora de la Reserva:</strong> " + sdf.format(reserva.getFechaHora()) + "</p>"
                + "<h2>Butacas Reservadas</h2>"
                + "<table>"
                + "<tr><th>Código</th><th>Categoría</th><th>Precio</th></tr>"
                + butacasHtml.toString()
                + "</table>"
                + "<h2>Detalles del Pago</h2>"
                + "<p><strong>ID de Pago:</strong> " + pago.getId() + "</p>"
                + "<p><strong>Fecha y Hora del Pago:</strong> " + sdf.format(pago.getFechaHora()) + "</p>"
                + "<p><strong>Estado del Pago:</strong> " + "Pendiente" + "</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>Este es un correo automático, por favor no responda a este mensaje.</p>"
                + "</div>"
                + "</body>"
                + "</html>";
    }
}
