package com.eventify.dao.implementations;

import com.eventify.dao.ReservaDao;
import com.eventify.entity.Reserva;
import com.eventify.entity.ReservaButaca;
import com.eventify.entity.ReservaButacaPK;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Membreño & nehem
 */
@ApplicationScoped
public class ReservaDaoImpl implements ReservaDao {

    @Inject
    private Session session;

    @Override
    public List<Reserva> findAll() {
        try {
            return session.createQuery("FROM Reserva r", Reserva.class)
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Reserva> findByDUIAndEvento(String dui, String evento) {
        try {
            String hql = "SELECT r FROM Reserva r WHERE 1=1";

            if (dui != null && !dui.isEmpty()) {
                hql += " AND r.idCliente.dui = :dui";
            }

            if (evento != null && !evento.isEmpty()) {
                hql += " AND r.idEvento.nombre LIKE :evento";
            }

            TypedQuery<Reserva> query = session.createQuery(hql, Reserva.class);

            if (dui != null && !dui.isEmpty()) {
                query.setParameter("dui", dui);
            }

            if (evento != null && !evento.isEmpty()) {
                query.setParameter("evento", "%" + evento + "%");
            }

            return query.getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Reserva> findById(int id) {
        try {
            return session.createQuery("FROM Reserva r WHERE r.id = :id", Reserva.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Optional.empty();
        }
    }
    
    @Override
    public List<Reserva> findReservasByCliente(int idCliente) {
        try {
            return session.createQuery("SELECT r FROM Reserva r WHERE r.idCliente.id = :idCliente ORDER BY r.fechaHora DESC", Reserva.class)
                 .setParameter("idCliente", idCliente)
                 .getResultList();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void save(Reserva reserva, List<ReservaButaca> reservaButacas) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(reserva);
            reservaButacas.forEach((rb) -> {
                rb.setReservaButacaPK(new ReservaButacaPK(reserva.getId(), rb.getButaca().getId()));
                session.save(rb);
            });
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            
            throw e;
        }
    }
}
