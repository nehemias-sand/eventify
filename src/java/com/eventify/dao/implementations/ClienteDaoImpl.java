package com.eventify.dao.implementations;

import com.eventify.dao.ClienteDao;
import com.eventify.entity.Cliente;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nehem
 */
@ApplicationScoped
public class ClienteDaoImpl implements ClienteDao {

    @Inject
    private Session session;

    @Override
    public List<Cliente> findAll() {
        try {
            return session.createQuery("FROM Cliente c", Cliente.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Cliente> findById(int id) {
        try {
            return session.createQuery("FROM Cliente c WHERE c.id = :id", Cliente.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Optional.empty();
        }
    }

    @Override
    public void save(Cliente cliente) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(cliente.getIdUsuario());
            session.save(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Cliente cliente) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Cliente cliente) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.err.println(e.getLocalizedMessage());
        }

    }

}
