package com.eventify.dao.implementations;

import com.eventify.dao.OrganizadorDao;
import com.eventify.entity.Organizador;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Diego
 */
@ApplicationScoped
public class OrganizadorDaoImpl implements OrganizadorDao {
    
    @Inject
    private Session session;

    @Override
    public List<Organizador> findAll() {
        try {
            return session.createQuery("FROM Organizador o", Organizador.class)
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }
    
    @Override
    public List<Organizador> findByEstadoActivo() {
        try {
            return session.createQuery("FROM Organizador o WHERE o.estado = true", Organizador.class)
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Organizador> findById(int id) {
        try {
            return session.createQuery("FROM Organizador o WHERE o.id = :id", Organizador.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Optional.empty();
        }
    }

    @Override
    public void save(Organizador organizador) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(organizador);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Organizador organizador) {
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.update(organizador);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Organizador organizador) {
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.delete(organizador);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println(e.getLocalizedMessage());
        }
    }

}
