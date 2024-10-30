package com.eventify.dao.implementations;

import com.eventify.dao.UsuarioDao;
import com.eventify.entity.Usuario;
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
public class UsuarioDaoImp implements UsuarioDao {

    @Inject
    private Session session;
    
    @Override
    public List<Usuario> findAll() {
        try {
            return session.createQuery("FROM Usuario u", Usuario.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        try {
            return session.createQuery("FROM Usuario u WHERE u.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Usuario> findById(int id) {
        try {
            return session.createQuery("FROM Usuario u WHERE u.id = :id", Usuario.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Optional.empty();
        }
    }

    @Override
    public void save(Usuario usuario) {
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Usuario usuario) {
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Usuario usuario) {
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.delete(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            
            System.err.println(e.getLocalizedMessage());
        }
    }

}
