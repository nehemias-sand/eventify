package com.eventify.dao.implementations;

import com.eventify.dao.ButacaDao;
import com.eventify.entity.Butaca;
import com.eventify.entity.Evento;
import com.eventify.entity.Instalacion;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Membreño & nehem
 */
@ApplicationScoped
public class ButacaDaoImpl implements ButacaDao {

    @Inject
    private Session session;

    @Override
    public List<Butaca> findAll() {
        try {
            return session.createQuery("FROM Butaca b", Butaca.class)
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Butaca> findById(int id) {
        try {
            return session.createQuery("FROM Butaca b WHERE b.id = :id", Butaca.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Butaca> findByIdReserva(int idReserva) {
        try {
            String hql = "SELECT rb.butaca FROM ReservaButaca rb WHERE rb.reserva.id = :idReserva";
            Query<Butaca> query = session.createQuery(hql);
            query.setParameter("idReserva", idReserva);

            return query.list();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Butaca> findByInstalacion(Instalacion instalacion) {
        try {
            return session.createQuery("FROM Butaca b WHERE b.idInstalacion = :instalacion", Butaca.class)
                    .setParameter("instalacion", instalacion)
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Butaca> findDisponiblesByEvento(Evento evento) {
        try {
            return session.createQuery("FROM Butaca b WHERE b.idInstalacion = :instalacion AND b.id NOT IN (SELECT rb.butaca.id FROM ReservaButaca rb WHERE rb.reserva.idEvento = :evento)", Butaca.class)
                    .setParameter("instalacion", evento.getIdInstalalacion())
                    .setParameter("evento", evento)
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }
}
