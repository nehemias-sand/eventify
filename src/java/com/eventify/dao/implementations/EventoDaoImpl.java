package com.eventify.dao.implementations;

import com.eventify.dao.EventoDao;

import com.eventify.entity.Evento;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Membreño
 */
@ApplicationScoped
public class EventoDaoImpl implements EventoDao{
    
    @Inject
    private Session session;

    @Override
    public List<Evento> findAll() {
        try {
            return session.createQuery("FROM Evento e " +
                                       "LEFT JOIN FETCH e.idOrganizador " +
                                       "LEFT JOIN FETCH e.idInstalalacion " +
                                       "LEFT JOIN FETCH e.idCategoria " +
                                       "LEFT JOIN FETCH e.idEstado ORDER BY e.fechaHora ASC", Evento.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Evento> findById(int id) {
        try {
            return session.createQuery("FROM Evento e WHERE e.id = :id", Evento.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Optional.empty();
        }
    }
    
    @Override
    public List<Evento> findByNombre(String nombre) {
        try {
            String hql = "FROM Evento e WHERE LOWER(e.nombre) LIKE(:nombre)";
            Query query = session.createQuery(hql);
            query.setParameter("nombre", "%" + nombre + "%");
            
            return query.list();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void save(Evento evento) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(evento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Evento evento) {
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.update(evento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Evento evento) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(evento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.err.println(e.getLocalizedMessage());
        }
    }
}
