package com.eventify.dao.implementations;

import com.eventify.dao.InstalacionDao;
import com.eventify.entity.Instalacion;
import com.eventify.entity.Instalacion;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.hibernate.Session;

/**
 *
 * @author Membreño
 */
@ApplicationScoped
public class InstalacionDaoImpl implements InstalacionDao{

     @Inject
    private Session session;

    @Override
    public List<Instalacion> findAll() {
        try {
            return session.createQuery("FROM Instalacion i", Instalacion.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Instalacion> findById(int id) {
        try {
            return session.createQuery("FROM Instalacion i WHERE i.id = :id", Instalacion.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Optional.empty();
        }
    }
}
