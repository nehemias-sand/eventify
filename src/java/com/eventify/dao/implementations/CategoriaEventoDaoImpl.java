package com.eventify.dao.implementations;

import com.eventify.dao.CategoriaEventoDao;
import com.eventify.entity.CategoriaEvento;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Membreño
 */
@ApplicationScoped
public class CategoriaEventoDaoImpl implements CategoriaEventoDao{
    
    @Inject
    private Session session;
    
    @Override
    public List<CategoriaEvento> findAll() {
        try {
            return session.createQuery("FROM CategoriaEvento ce", CategoriaEvento.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }
    
    @Override
    public List<CategoriaEvento> findByEstadoActivo() {
        try {
            return session.createQuery("FROM CategoriaEvento ce WHERE ce.estado = true", CategoriaEvento.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }
    
    @Override
    public Optional<CategoriaEvento> findById(int id) {
        try {
            return session.createQuery("FROM CategoriaEvento ce WHERE ce.id = :id", CategoriaEvento.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Optional.empty();
        }
    }

    @Override
    public void save(CategoriaEvento categoriaEvento) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(categoriaEvento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void update(CategoriaEvento categoriaEvento) {
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.update(categoriaEvento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(CategoriaEvento categoriaEvento) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(categoriaEvento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.err.println(e.getLocalizedMessage());
        }
    }

    
}
