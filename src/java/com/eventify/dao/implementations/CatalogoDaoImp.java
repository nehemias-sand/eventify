package com.eventify.dao.implementations;

import com.eventify.dao.CatalogoDao;
import com.eventify.entity.CategoriaButaca;
import com.eventify.entity.CategoriaEvento;
import com.eventify.entity.EstadoEvento;
import com.eventify.entity.EstadoPago;
import com.eventify.entity.Genero;
import com.eventify.entity.Rol;
import com.eventify.entity.TipoPago;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.hibernate.Session;

/**
 *
 * @author nehem
 */
@ApplicationScoped
public class CatalogoDaoImp implements CatalogoDao {

    @Inject
    private Session session;

    @Override
    public List<Rol> findAllRoles() {
        try {
            return session.createQuery("FROM Rol r", Rol.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Rol> findRolByNombre(String nombre) {
        try {
            return session.createQuery("FROM Rol r WHERE r.nombre = :nombre", Rol.class)
                    .setParameter("nombre", nombre)
                    .uniqueResultOptional();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Genero> findAllGeneros() {
        try {
            return session.createQuery("FROM Genero r", Genero.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<EstadoEvento> findAllEstadosEvento() {
        try {
            return session.createQuery("FROM EstadoEvento ee", EstadoEvento.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<CategoriaEvento> findAllCategoriasEvento() {
        try {
            return session.createQuery("FROM CategoriaEvento ce", CategoriaEvento.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<CategoriaButaca> findAllCategoriasButaca() {
       try {
            return session.createQuery("FROM CategoriaButaca cb", CategoriaButaca.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<TipoPago> findAllTiposPago() {
        try {
            return session.createQuery("FROM TipoPago tp", TipoPago.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<EstadoPago> findAllEstadosPago() {
        try {
            return session.createQuery("FROM EstadoPago tp", EstadoPago.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

}
